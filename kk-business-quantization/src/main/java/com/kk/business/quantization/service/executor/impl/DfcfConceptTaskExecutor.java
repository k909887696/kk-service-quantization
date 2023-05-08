package com.kk.business.quantization.service.executor.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kk.business.quantization.dao.entity.Concept;
import com.kk.business.quantization.dao.mapper.ConceptMapper;
import com.kk.business.quantization.model.dfcf.DfcfData;
import com.kk.business.quantization.model.tushare.ConceptVo;
import com.kk.business.quantization.model.tushare.TushareData;
import com.kk.business.quantization.service.IConceptService;
import com.kk.business.quantization.service.executor.ITaskExecutor;
import com.kk.business.quantization.third.IDfcfDataApi;
import com.kk.business.quantization.third.ITushareDataApi;
import com.kk.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 东方财富概念基本信息任务执行器
 */
@Service("DfcfConceptTaskExecutor")
@Slf4j
public class DfcfConceptTaskExecutor implements ITaskExecutor {

    @Resource
    public IDfcfDataApi dfcfDataApi;
    @Resource
    public IConceptService conceptService;
    @Resource
    public ConceptMapper conceptMapper;


    /**
     * 下载数据
     * @param params
     */
    @Override
    public void executeTask(String params) {
        if(StringUtils.isBlank(params)) return ;
        ConceptVo vo = (ConceptVo) JsonUtil.parseObject(params,ConceptVo.class);
        List<Concept> data = new ArrayList<>();

        DfcfData<Concept> res =  dfcfDataApi.concept(vo);
        if("cover".equals(vo.getUpdateType()))
        {
            LambdaQueryWrapper<Concept> query = new LambdaQueryWrapper<>();
            query.eq(Concept::getSrc,"ts");
            conceptMapper.delete(query);
        }
        data.addAll(res.getData());
        //插入db
        conceptService.insertIgnoreBatch(data);
    }


}
