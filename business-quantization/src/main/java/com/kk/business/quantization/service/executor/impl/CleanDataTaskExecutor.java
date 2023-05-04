package com.kk.business.quantization.service.executor.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kk.business.quantization.dao.entity.CnM;
import com.kk.business.quantization.dao.entity.CollectionPolicy;
import com.kk.business.quantization.dao.entity.CollectionTaskHistory;
import com.kk.business.quantization.dao.mapper.CollectionTaskHistoryMapper;
import com.kk.business.quantization.model.tushare.CnmVo;
import com.kk.business.quantization.model.tushare.DailyVo;
import com.kk.business.quantization.model.tushare.TushareData;
import com.kk.business.quantization.model.vo.CleanDataVo;
import com.kk.business.quantization.service.ICnMService;
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
 * 清理历史数据
 */
@Service("CleanDataTaskExecutor")
@Slf4j
public class CleanDataTaskExecutor implements ITaskExecutor {

    @Resource
    public ITushareDataApi tushareDataApi;
    @Resource
    public CollectionTaskHistoryMapper collectionTaskHistoryMapper;


    /**
     * 清理数据
     * @param params
     */
    @Override
    public void executeTask(String params) {
        if(StringUtils.isBlank(params)) return ;
        CleanDataVo vo = (CleanDataVo) JsonUtil.parseObject(params,CleanDataVo.class);

        //清理历史任务
        LambdaQueryWrapper<CollectionTaskHistory> cthQuery = new LambdaQueryWrapper<>();
        cthQuery.le(CollectionTaskHistory::getCreateTime,DateUtil.addDate(new Date(), Calendar.DATE,-1*vo.getHoldDataDay()));
        collectionTaskHistoryMapper.delete(cthQuery);
    }
    @Override
    public String paramsHandler(String params, CollectionPolicy policy) {
        if(StringUtils.isBlank(params)) return params;
        CleanDataVo vo = (CleanDataVo) JsonUtil.parseObject(params,CleanDataVo.class);

        if("today".equals(vo.getCleanDate()))
        {
            vo.setCleanDate(DateUtil.date2String(new Date(),DateUtil.PATTERN_STANDARD08W));
        }
        return JsonUtil.getJSONString(vo);
    }

}
