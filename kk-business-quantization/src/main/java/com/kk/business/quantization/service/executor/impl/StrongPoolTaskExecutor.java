package com.kk.business.quantization.service.executor.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.*;
import com.kk.business.quantization.model.dto.DailyKdjDto;
import com.kk.business.quantization.model.dto.DailyLeaderDto;
import com.kk.business.quantization.model.dto.DailyListDto;
import com.kk.business.quantization.model.dto.StockBasicListDto;
import com.kk.business.quantization.model.po.tushare.DailyKdjVo;
import com.kk.business.quantization.model.vo.*;
import com.kk.business.quantization.service.*;
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
    @Resource
    public IStockBasicService stockBasicService;
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
        StringBuilder html = new StringBuilder();
        //获取强势概念
        List<String> conceptIds = new ArrayList<>();
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
                put("style","background-color: #50e3e3;");
            }};
            dataList.add(item);
            conceptIds.add(dto.getTsCode());
            searchDailyLeaderVo.setPageSize(5);
            searchDailyLeaderVo.setConceptId(dto.getTsCode());
            PageResult<DailyLeaderDto> stockLeaderList = dailyService.selectStockLeader(searchDailyLeaderVo);
            if(stockLeaderList !=null && stockLeaderList.getResult() !=null)
            {
                for (DailyLeaderDto sdto : stockLeaderList.getResult()) {
                    LinkedHashMap sitem = new LinkedHashMap() {{
                        put("tsCode", sdto.getTsCode());
                        put("name", sdto.getName());
                        put("high", sdto.getHigh());
                        put("low", sdto.getLow());
                        put("startClose", sdto.getStartClose());
                        put("endClose", sdto.getEndClose());
                        put("rangePct", sdto.getRangePct());
                        put("rollBackPct", sdto.getRollBackPct());
                        put("maxPct", sdto.getMaxPct());
                    }};
                    dataList.add(sitem);
                }
            }
        }

        //获取强势概念下 kdj 运算的股票
        StockBasicListVo stockBasicListVo = new StockBasicListVo();
        stockBasicListVo.setConceptIds(conceptIds);
        stockBasicListVo.setKdjCrossDate(vo.getTradeDate());
        stockBasicListVo.setPageIndex(1);
        stockBasicListVo.setPageSize(1000);
        PageResult<StockBasicListDto>  stockBasicListDtoPageResult = stockBasicService.selectPageList(stockBasicListVo);
        if(stockBasicListDtoPageResult != null && stockBasicListDtoPageResult.getResult() != null)
        {
            List<String> kdjCrossTsCodes = stockBasicListDtoPageResult.getResult().stream().map(t->t.getTsCode()).collect(Collectors.toList());
            SearchDailyVo searchDailyVo = new SearchDailyVo();
            searchDailyVo.setIds(kdjCrossTsCodes);
            searchDailyVo.setTradeDate(vo.getTradeDate());
            PageResult<DailyKdjDto> dailyKdjDtoPageResult = dailyService.getPageResultEx(searchDailyVo);
            LinkedHashMap stockHead = new LinkedHashMap(){{
                put("tsCode","代码");
                put("name","名称");
                put("symbol","股票代码");
                put("high","最高价");
                put("low","最低价");
                put("open","开盘价");
                put("close","收盘价");
                put("pctChg","涨幅");
                put("amount","成交额 （千元）");

            }};
            List<LinkedHashMap<String,Object>> stockDataList = new ArrayList<>();

            for (StockBasicListDto sDto :stockBasicListDtoPageResult.getResult()) {

                if(dailyKdjDtoPageResult!=null && dailyKdjDtoPageResult.getResult()!=null)
                {
                    Optional< DailyKdjDto> dailyKdjDto = dailyKdjDtoPageResult.getResult().stream().filter(t->t.getTsCode().equals(sDto.getTsCode())).findFirst();
                    if(dailyKdjDto!=null) {
                        LinkedHashMap item = new LinkedHashMap() {
                            {
                                put("tsCode", sDto.getTsCode());
                                put("name", sDto.getName());
                                put("symbol", sDto.getSymbol());

                                put("high", dailyKdjDto.get().getHigh());
                                put("low", dailyKdjDto.get().getLow());
                                put("open", dailyKdjDto.get().getOpen());
                                put("close", dailyKdjDto.get().getClose());
                                put("pctChg", dailyKdjDto.get().getPctChg());
                                put("amount", dailyKdjDto.get().getAmount());

                            }
                        };
                        stockDataList.add(item);
                    }
                }


            }
            StringBuilder stockTable= htmlUtil.genHtmlTable(String.format("%s ~ %s 期间强势股中KDJ交叉点",startTradeCal.getCalDate(),endTradeCal.getCalDate()),stockHead,stockDataList);
            html.append(stockTable);
        }
        StringBuilder table= htmlUtil.genHtmlTable(String.format("%s ~ %s 期间强势股",startTradeCal.getCalDate(),endTradeCal.getCalDate()),head,dataList);


        html.append(table);
        EmailSendMsg msg = new EmailSendMsg();
        msg.setFrom("909887696@qq.com");
        msg.setText(html.toString());
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
