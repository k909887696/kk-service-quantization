package com.kk.business.quantization.service.executor.impl;

import com.kk.business.quantization.dao.entity.*;
import com.kk.business.quantization.model.DailyKdj;
import com.kk.business.quantization.model.tushare.DailyKdjVo;
import com.kk.business.quantization.model.tushare.DailyVo;
import com.kk.business.quantization.model.tushare.TushareData;
import com.kk.business.quantization.model.vo.SearchDailyVo;
import com.kk.business.quantization.service.IDailyService;
import com.kk.business.quantization.service.IStockBasicService;
import com.kk.business.quantization.service.IStockDayKdjService;
import com.kk.business.quantization.service.ITradeCalService;
import com.kk.business.quantization.service.executor.ITaskExecutor;
import com.kk.business.quantization.third.ITushareDataApi;
import com.kk.business.quantization.utils.StochasticOscillatorUtil;
import com.kk.common.base.model.BasePage;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.DateUtil;
import com.kk.common.utils.JsonUtil;
import com.kk.common.utils.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 日线kdj任务执行器
 */
@Service("StockDayKdjTaskExecutor")
@Slf4j
public class StockDayKdjTaskExecutor implements ITaskExecutor {

    @Resource
    public ITushareDataApi tushareDataApi;
    @Resource
    public IDailyService dailyService;
    @Resource
    public IStockBasicService stockBasicService;
    @Resource
    public ITradeCalService tradeCalService;
    @Resource
    public IStockDayKdjService stockDayKdjService;
    @Resource
    public MapperUtils mapperUtils;

    /**
     * 下载数据
     * @param params
     */
    @Override
    public void executeTask(String params) {
        if(StringUtils.isBlank(params)) return ;
        DailyKdjVo vo = (DailyKdjVo) JsonUtil.parseObject(params,DailyKdjVo.class);
        List<StockDayKdj> data = new ArrayList<>();
        //处理kdj类型默认值
        int N = 9, M1 = 3, M2 = 3;
        if (StringUtils.isNotBlank(vo.getKdjType()))
        {
            String[] kdjNM = vo.getKdjType().split("_");
            if (kdjNM.length >= 3)
            {
                N = Integer.parseInt( kdjNM[0]);
                M1 = Integer.parseInt( kdjNM[1]);
                M2 = Integer.parseInt( kdjNM[2]);
                if (N <= 0) N = 9;
                if (M1 <= 0) M1 = 3;
                if (M2 <= 0) M2 = 3;
            }
        }
        if( StringUtils.isBlank(vo.getTradeDate()))  //ts_code存在的情况 且 trade_date不存在
        {
            if(vo.getIds() ==null)
            {
                vo.setIds(new ArrayList<>());
            }
            if(StringUtils.isNotBlank(vo.getTsCode()))
                vo.getIds().add(vo.getTsCode());
            SearchDailyVo sdVo = mapperUtils.map(vo,SearchDailyVo.class);
            sdVo.setPageIndex(1);
            sdVo.setPageSize(50000);
            PageResult<DailyKdj> res =  dailyService.getPageResultEx(sdVo);
            if(res ==null || res.getResult()==null || res.getTotalCount()<=0)
                return ;
            for (String id : vo.getIds())
            {
               List<DailyKdj> tempList = res.getResult().stream()
                       .filter(t->t.getTsCode().equals(id))
                       .sorted(Comparator.comparing(DailyKdj::getTradeDate))
                       .collect(Collectors.toList());
               List<StockDayKdj> stockDayKdjList = StochasticOscillatorUtil.ComputationKDJ(N,M1,M2,tempList);
               data.addAll(stockDayKdjList);
            }
        }else {
            stockDayKdjService.deleteByTradeDate(vo.getTradeDate());
            SearchDailyVo sdVo = mapperUtils.map(vo,SearchDailyVo.class);
            String nowDate = vo.getTradeDate();
            TradeCal nowOpenTradeCal = tradeCalService.getRecentlyOpenByDay(nowDate,N,"asc");
            String nowOpenDate = nowOpenTradeCal==null ?nowDate:nowOpenTradeCal.getCalDate();
            sdVo.setStartDate(nowOpenDate);
            sdVo.setEndDate(vo.getTradeDate());
            sdVo.setTradeDate(null);
            sdVo.setPageIndex(1);
            sdVo.setPageSize(50000);
            PageResult<DailyKdj> res =  dailyService.getPageResultEx(sdVo);
            if(res ==null || res.getResult()==null || res.getTotalCount()<=0) return ;
            log.info(JsonUtil.getJSONString(res.getResult()));
            for (String id : vo.getIds())
            {
                List<DailyKdj> tempList = res.getResult().stream()
                        .filter(t->t.getTsCode().equals(id))
                        .sorted(Comparator.comparing(DailyKdj::getTradeDate))
                        .collect(Collectors.toList());
                List<StockDayKdj> stockDayKdjsList = StochasticOscillatorUtil.ComputationKDJ(N,M1,M2,tempList);
                data.addAll(stockDayKdjsList);
            }

        }
        //插入db
        stockDayKdjService.insertIgnoreBatch(data);
    }

    @Override
    public List<CollectionTask> splitTask(CollectionPolicy policy) {
        DailyKdjVo vo = (DailyKdjVo) JsonUtil.parseObject(policy.getInvokeParams(),DailyKdjVo.class);

        List<CollectionTask> list = new ArrayList<>();
        if(StringUtils.isBlank(vo.getTsCode()))
        {
            int pageSize = 5000,pageIndex =1;
            BasePage basePage = new BasePage();
            basePage.setPageIndex(pageIndex);
            basePage.setPageSize(pageSize);
            PageResult<StockBasic> stockBasicPageResult = stockBasicService.getStockBasicPageResult(basePage);
            if(stockBasicPageResult !=null && stockBasicPageResult.getTotalCount()>0 )
            {
                List<StockBasic> stockBasicList = stockBasicPageResult.getResult();
                if(stockBasicPageResult.getTotalCount()>pageSize) {
                    long sbPage = stockBasicPageResult.getTotalCount() / pageSize;
                    if (sbPage * pageSize < stockBasicPageResult.getTotalCount()) {
                        sbPage++;
                    }
                    for (pageIndex++; pageIndex <= sbPage; pageIndex++) {
                        basePage.setPageIndex(pageIndex);
                        basePage.setPageSize(pageSize);
                        PageResult<StockBasic> tempS = stockBasicService.getStockBasicPageResult(basePage);
                        if(tempS!=null && tempS.getTotalCount()>0)
                        {
                            stockBasicList.addAll(tempS.getResult());
                        }
                    }
                }
                int splitPage = stockBasicList.size() / vo.getPageSize();
                if(splitPage * vo.getPageSize() < stockBasicList.size())
                {
                    splitPage ++;
                }
                for (int i =0;i<splitPage;i++)
                {
                   List<String> tsCodeList = stockBasicList.stream()
                           .skip(i*vo.getPageSize())
                           .limit(vo.getPageSize())
                           .map(StockBasic::getTsCode).collect(Collectors.toList());
                   vo.setIds(tsCodeList);
                   vo.setPageIndex(i+1);
                   CollectionTask task = generateTask(policy);
                   task.setInvokeParams(this.paramsHandler(JsonUtil.getJSONString(vo),policy));
                   list.add(task);
                }
            }
        }else {
            CollectionTask task = generateTask(policy);
            task.setInvokeParams(this.paramsHandler(task.getInvokeParams(),policy));
            list.add(task);
        }

        return list;
    }

    @Override
    public String paramsHandler(String params,CollectionPolicy policy) {
        if(StringUtils.isBlank(params)) return params;
        DailyKdjVo vo = (DailyKdjVo) JsonUtil.parseObject(params,DailyKdjVo.class);

        if("today".equals(vo.getTradeDate()))
        {
            vo.setTradeDate(DateUtil.date2String(policy.getPreRunTime(),DateUtil.PATTERN_STANDARD08W));
        }
        return JsonUtil.getJSONString(vo);
    }
}
