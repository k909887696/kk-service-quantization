package com.kk.business.quantization.service.executor.impl;

import com.kk.business.quantization.dao.entity.*;
import com.kk.business.quantization.model.po.tushare.DailyVo;
import com.kk.business.quantization.model.po.tushare.MoneyFlowVo;
import com.kk.business.quantization.model.po.tushare.TushareData;
import com.kk.business.quantization.model.vo.StockBasicList4InnVo;
import com.kk.business.quantization.service.IMoneyFlowService;
import com.kk.business.quantization.service.IStockBasicService;
import com.kk.business.quantization.service.executor.ITaskExecutor;
import com.kk.business.quantization.third.ITushareDataApi;
import com.kk.common.base.model.BasePage;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.DateUtil;
import com.kk.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 个股资金流向任务执行器
 */
@Service("MoneyFlowTaskExecutor")
@Slf4j
public class MoneyFlowTaskExecutor implements ITaskExecutor {

    @Resource
    public ITushareDataApi tushareDataApi;
    @Resource
    public IMoneyFlowService moneyFlowService;

    @Resource
    public IStockBasicService stockBasicService;

    /**
     * 下载tushare日线行情数据
     * @param params
     */
    @Override
    public void executeTask(String params) {
        if(StringUtils.isBlank(params)) return ;
        MoneyFlowVo vo = (MoneyFlowVo) JsonUtil.parseObject(params,MoneyFlowVo.class);
        List<MoneyFlow> data = new ArrayList<>();
        if((StringUtils.isNotBlank(vo.getTsCode()) || (vo.getIds() !=null && vo.getIds().size() >0) )&& StringUtils.isBlank(vo.getTradeDate()))  //ts_code存在的情况 且 trade_date不存在
        {

            if(vo.getIds() ==null)
            {
                vo.setIds(new ArrayList<>());
            }
            if(StringUtils.isNotBlank(vo.getTsCode()))
                vo.getIds().add(vo.getTsCode());
            for (int i=0;i<vo.getIds().size();i++)
            {
                vo.setTsCode(vo.getIds().get(i));
                TushareData<MoneyFlow> res =  tushareDataApi.moneyFlow(vo);
                data.addAll(res.getData());
            }
        }else {
            TushareData<MoneyFlow> res =  tushareDataApi.moneyFlow(vo);
            data.addAll(res.getData());
        }

        //插入db
        moneyFlowService.insertIgnoreBatch(data);
    }

    @Override
    public List<CollectionTask> splitTask(CollectionPolicy policy) {
        MoneyFlowVo vo = (MoneyFlowVo) JsonUtil.parseObject(policy.getInvokeParams(),MoneyFlowVo.class);

        List<CollectionTask> list = new ArrayList<>();
        if(StringUtils.isBlank(vo.getTsCode()) && StringUtils.isBlank(vo.getTradeDate()))
        {
            int pageSize = 5000,pageIndex =1;
            StockBasicList4InnVo basePage = new StockBasicList4InnVo();
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
        DailyVo vo = (DailyVo) JsonUtil.parseObject(params,DailyVo.class);

        if("today".equals(vo.getTradeDate()))
        {
            vo.setTradeDate(DateUtil.date2String(new Date(),DateUtil.PATTERN_STANDARD08W));
        }
        return JsonUtil.getJSONString(vo);
    }
}
