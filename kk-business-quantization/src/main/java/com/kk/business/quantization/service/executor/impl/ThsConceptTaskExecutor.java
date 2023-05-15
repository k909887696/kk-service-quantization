package com.kk.business.quantization.service.executor.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kk.business.quantization.dao.entity.Concept;
import com.kk.business.quantization.dao.mapper.ConceptMapper;
import com.kk.business.quantization.model.po.tushare.ConceptVo;
import com.kk.business.quantization.model.po.tushare.TushareData;
import com.kk.business.quantization.service.IConceptService;
import com.kk.business.quantization.service.executor.ITaskExecutor;
import com.kk.business.quantization.third.ITushareDataApi;
import com.kk.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 同花顺概念基本信息任务执行器
 */
@Service("ThsConceptTaskExecutor")
@Slf4j
public class ThsConceptTaskExecutor implements ITaskExecutor {

    @Resource
    public ITushareDataApi tushareDataApi;
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

        TushareData<Concept> res =  tushareDataApi.thsIndex(vo);
        for(int i=0;i<res.getData().size();i++)
        {
            res.getData().get(i).setSrc("ths");
        }
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
