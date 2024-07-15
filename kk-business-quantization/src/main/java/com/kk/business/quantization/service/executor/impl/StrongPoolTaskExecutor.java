package com.kk.business.quantization.service.executor.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.*;
import com.kk.business.quantization.model.dto.DailyKdjDto;
import com.kk.business.quantization.model.dto.DailyLeaderDto;
import com.kk.business.quantization.model.dto.DailyListDto;
import com.kk.business.quantization.model.po.tushare.DailyKdjVo;
import com.kk.business.quantization.model.vo.DailyListVo;
import com.kk.business.quantization.model.vo.SearchDailyLeaderVo;
import com.kk.business.quantization.model.vo.SearchDailyVo;
import com.kk.business.quantization.model.vo.StrongPoolTaskExecutorVo;
import com.kk.business.quantization.service.IConceptDailyService;
import com.kk.business.quantization.service.IDailyService;
import com.kk.business.quantization.service.IKdjCrossService;
import com.kk.business.quantization.service.ITradeCalService;
import com.kk.business.quantization.service.executor.ITaskExecutor;
import com.kk.business.quantization.third.ITushareDataApi;
import com.kk.business.quantization.utils.StochasticOscillatorUtil;
import com.kk.business.quantization.utils.htmlUtil;
import com.kk.common.base.email.EmailSendMsg;
import com.kk.common.base.email.EmailUtil;
import com.kk.common.base.model.PageResult;
import com.kk.common.exception.BusinessException;
import com.kk.common.utils.DateUtil;
import com.kk.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.text.html.HTMLDocument;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 强势股池运算数据任务执行器
 */
@Service("StrongPoolTaskExecutor")
@Slf4j
public class StrongPoolTaskExecutor implements ITaskExecutor {

    @Resource
    public ITushareDataApi tushareDataApi;
    @Resource
    public IDailyService dailyService;
    @Resource
    public IKdjCrossService kdjCrossService;
    @Resource
    public ITradeCalService tradeCalService;
    @Resource
    public IConceptDailyService conceptDailyService;
    @Resource
    public EmailUtil emailUtil;
    /**
     * 下载数据
     * @param params
     */
    @Override
    public void executeTask(String params) {
        if(StringUtils.isBlank(params)) return ;
        StrongPoolTaskExecutorVo vo = (StrongPoolTaskExecutorVo) JsonUtil.parseObject(params,StrongPoolTaskExecutorVo.class);

        if(StringUtils.isBlank(vo.getTradeDate())) {
            throw new BusinessException("交易日期为空！");
        }
        Date n = new Date();
        TradeCal startTradeCal = tradeCalService.getRecentlyOpenByDay(vo.getTradeDate(),20,"asc");
        TradeCal endTradeCal = tradeCalService.getRecentlyOpenByDay(vo.getTradeDate(),1,"desc");
        SearchDailyLeaderVo searchDailyLeaderVo = new SearchDailyLeaderVo();
        searchDailyLeaderVo.setStartDate(startTradeCal.getCalDate());
        searchDailyLeaderVo.setEndDate(endTradeCal.getCalDate());
        searchDailyLeaderVo.setPageIndex(1);
        searchDailyLeaderVo.setPageSize(20);
        PageResult<DailyLeaderDto> conceptLeaderList = conceptDailyService.selectConceptLeaderListByRange(searchDailyLeaderVo);


        LinkedHashMap head = new LinkedHashMap(){{
                        put("tsCode","代码");
                        put("name","名称");
                        put("high","区间最高价");
                        put("low","区间最低价");
                        put("startClose","开盘价");
                        put("endClose","收盘价");
                        put("rangePct","区间涨幅");
                        put("rollBackPct","区间回撤");
                        put("maxPct","区间最大涨幅");
        }};
        List<LinkedHashMap<String,Object>> dataList = new ArrayList<>();
        for(DailyLeaderDto dto : conceptLeaderList.getResult())
        {
            LinkedHashMap item = new LinkedHashMap(){{
                put("tsCode",dto.getTsCode());
                put("name",dto.getName());
                put("high",dto.getHigh());
                put("low",dto.getLow());
                put("startClose",dto.getStartClose());
                put("endClose",dto.getEndClose());
                put("rangePct",dto.getRangePct());
                put("rollBackPct",dto.getRollBackPct());
                put("maxPct",dto.getMaxPct());
            }};
            dataList.add(item);
        }


        StringBuilder html = new StringBuilder();
        StringBuilder table= htmlUtil.genHtmlTable(head,dataList,"");



       System.out.print(table.toString());
        EmailSendMsg msg = new EmailSendMsg();
        msg.setFrom("909887696@qq.com");
        msg.setText(table.toString());
        msg.setSubject("quantization-邮件通知");
        msg.setTo(new ArrayList<String>(){{add("909887696@qq.com");}});
        emailUtil.sendMimeMail(msg);
    }


    @Override
    public List<CollectionTask> splitTask(CollectionPolicy policy) {

        return null;
    }


    @Override
    public String paramsHandler(String params,CollectionPolicy policy) {
        if(StringUtils.isBlank(params)) return params;
        StrongPoolTaskExecutorVo vo = (StrongPoolTaskExecutorVo) JsonUtil.parseObject(params,StrongPoolTaskExecutorVo.class);

        if("today".equals(vo.getTradeDate()))
        {
            vo.setTradeDate(DateUtil.date2String(policy.getPreRunTime(),DateUtil.PATTERN_STANDARD08W));
        }
        return JsonUtil.getJSONString(vo);
    }
}
