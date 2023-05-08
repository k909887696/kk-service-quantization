package com.kk.business.quantization.service.executor.impl;

import com.kk.business.quantization.dao.entity.StockBasic;
import com.kk.business.quantization.dao.entity.TradeCal;
import com.kk.business.quantization.dao.mapper.StockBasicMapper;
import com.kk.business.quantization.model.tushare.StockBasicVo;
import com.kk.business.quantization.model.tushare.TradeCalVo;
import com.kk.business.quantization.model.tushare.TushareData;
import com.kk.business.quantization.service.IStockBasicService;
import com.kk.business.quantization.service.ITradeCalService;
import com.kk.business.quantization.service.executor.ITaskExecutor;
import com.kk.business.quantization.third.ITushareDataApi;
import com.kk.common.utils.DateUtil;
import com.kk.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 个股基本信息任务执行器
 */
@Service("TradeCalTaskExecutor")
@Slf4j
public class TradeCalTaskExecutor implements ITaskExecutor {

    @Resource
    public ITushareDataApi tushareDataApi;
    @Resource
    public ITradeCalService tradeCalService;


    /**
     * 下载数据
     * @param params
     */
    @Override
    public void executeTask(String params) {
        if(StringUtils.isBlank(params)) return ;
        TradeCalVo vo = (TradeCalVo) JsonUtil.parseObject(params,TradeCalVo.class);
        List<TradeCal> data = new ArrayList<>();
        if(StringUtils.isBlank(vo.getStartDate()))
        {
            Date d = DateUtil.addDate(new Date(), Calendar.YEAR,-1);
            vo.setStartDate(DateUtil.date2String(d,DateUtil.PATTERN_STANDARD08W));
        }
        TushareData<TradeCal> res =  tushareDataApi.tradeCal(vo);
        data.addAll(res.getData());
        //插入db
        tradeCalService.insertIgnoreBatch(data);
    }


}
