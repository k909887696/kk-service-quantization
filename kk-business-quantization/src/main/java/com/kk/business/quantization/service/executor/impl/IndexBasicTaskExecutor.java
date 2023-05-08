package com.kk.business.quantization.service.executor.impl;

import com.kk.business.quantization.dao.entity.CnM;
import com.kk.business.quantization.dao.entity.IndexBasic;
import com.kk.business.quantization.model.tushare.CnmVo;
import com.kk.business.quantization.model.tushare.IndexBasicVo;
import com.kk.business.quantization.model.tushare.TushareData;
import com.kk.business.quantization.service.ICnMService;
import com.kk.business.quantization.service.IIndexBasicService;
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
 * 指数基本信息
 */
@Service("IndexBasicTaskExecutor")
@Slf4j
public class IndexBasicTaskExecutor implements ITaskExecutor {

    @Resource
    public ITushareDataApi tushareDataApi;
    @Resource
    public IIndexBasicService indexBasicService;


    /**
     * 下载数据
     * @param params
     */
    @Override
    public void executeTask(String params) {
        if(StringUtils.isBlank(params)) return ;
        IndexBasicVo vo = (IndexBasicVo) JsonUtil.parseObject(params,IndexBasicVo.class);
        List<IndexBasic> data = new ArrayList<>();

        TushareData<IndexBasic> res =  tushareDataApi.indexBasic(vo);
        data.addAll(res.getData());
        //插入db
        indexBasicService.insertIgnoreBatch(data);
    }


}
