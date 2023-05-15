package com.kk.business.quantization.service.executor.impl;

import com.kk.business.quantization.dao.entity.*;
import com.kk.business.quantization.model.dto.DailyKdjDto;
import com.kk.business.quantization.model.po.tushare.DailyKdjVo;
import com.kk.business.quantization.model.vo.SearchDailyVo;
import com.kk.business.quantization.service.*;
import com.kk.business.quantization.service.executor.ITaskExecutor;
import com.kk.business.quantization.third.ITushareDataApi;
import com.kk.business.quantization.utils.StochasticOscillatorUtil;
import com.kk.common.base.model.PageResult;
import com.kk.common.exception.BusinessException;
import com.kk.common.utils.DateUtil;
import com.kk.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 日线kdj交叉数据任务执行器
 */
@Service("KdjCrossTaskExecutor")
@Slf4j
public class KdjCrossTaskExecutor implements ITaskExecutor {

    @Resource
    public ITushareDataApi tushareDataApi;
    @Resource
    public IDailyService dailyService;
    @Resource
    public IKdjCrossService kdjCrossService;
    @Resource
    public ITradeCalService tradeCalService;


    /**
     * 下载数据
     * @param params
     */
    @Override
    public void executeTask(String params) {
        if(StringUtils.isBlank(params)) return ;
        DailyKdjVo vo = (DailyKdjVo) JsonUtil.parseObject(params,DailyKdjVo.class);
        List<KdjCross> data = new ArrayList<>();

        if(StringUtils.isBlank(vo.getTradeDate())) {
            throw new BusinessException("交易日期为空！");
        }
        if(StringUtils.isBlank(vo.getKdjType()))
            vo.setKdjType("9_3_3");
        String nowDateStr = StringUtils.isNotBlank(vo.getTradeDate()) ? vo.getTradeDate(): DateUtil.date2String(new Date(),DateUtil.PATTERN_STANDARD08W);
        TradeCal endOpenDate = tradeCalService.getRecentlyOpenByDay(nowDateStr,1,"desc");
        TradeCal startOpenDate = tradeCalService.getRecentlyOpenByDay(nowDateStr,2,"asc");
        SearchDailyVo svo = new SearchDailyVo();
        svo.setStartDate(startOpenDate.getCalDate());
        svo.setEndDate(endOpenDate.getCalDate());

        svo.setPageIndex(1);
        svo.setPageSize(50000);
        PageResult<DailyKdjDto> dailyKdjList = dailyService.getPageResultEx(svo);
        if(dailyKdjList==null||dailyKdjList.getTotalCount()<=0) return ;
        List<String> ids = dailyKdjList.getResult().stream().map(t->t.getTsCode()).collect(Collectors.toList());
        for (String code : ids)
        {
           List<DailyKdjDto> templist =  dailyKdjList.getResult().stream().filter(t->t.getTsCode().equals(code)).collect(Collectors.toList());
           String crossType = StochasticOscillatorUtil.IsKDJCrossAndType(templist);
           if(StringUtils.isNotBlank(crossType))
           {
               KdjCross kdjCross = new KdjCross();
               kdjCross.setCrossType(crossType);
               kdjCross.setTsCode(templist.get(0).getTsCode());
               kdjCross.setTradeDate(templist.get(0).getTradeDate());
               kdjCross.setAnalysisType(vo.getKdjType());
               data.add(kdjCross);
           }
        }
        //插入db
        kdjCrossService.insertIgnoreBatch(data);
    }

    @Override
    public List<CollectionTask> splitTask(CollectionPolicy policy) {
        DailyKdjVo vo = (DailyKdjVo) JsonUtil.parseObject(policy.getInvokeParams(), DailyKdjVo.class);
        List<CollectionTask> list = new ArrayList<>();
        if(!StringUtils.isBlank(vo.getTradeDate()))
        {
            CollectionTask task = generateTask(policy);
            task.setInvokeParams(this.paramsHandler(task.getInvokeParams(),policy));
            list.add(task);
        }else {
            Date sDate = new Date();
            Date eDate = new Date();
            if(!StringUtils.isBlank(vo.getStartDate()))
            {
                sDate = DateUtil.string2Date(vo.getStartDate(),DateUtil.PATTERN_STANDARD08W);
            }
            if(!StringUtils.isBlank(vo.getEndDate()))
            {
                eDate = DateUtil.string2Date(vo.getEndDate(),DateUtil.PATTERN_STANDARD08W);
            }
            long spanTime = eDate.getTime() - sDate.getTime();
            if(spanTime < 0)
            {
                throw new BusinessException("开始时间比结束时间大，请检查参数！");
            }
            int spanDay = Math.abs((int) ((spanTime) / (1000 * 3600 * 24)));
            for(int i=0;i<=spanDay;i++)
            {
                CollectionTask task = generateTask(policy);
                vo.setTradeDate(DateUtil.date2String(DateUtil.addDate(sDate, Calendar.DATE,i),DateUtil.PATTERN_STANDARD08W));
                task.setInvokeParams(this.paramsHandler(JsonUtil.getJSONString(vo),policy));
                list.add(task);
            }
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
