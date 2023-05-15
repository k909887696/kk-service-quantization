package com.kk.business.quantization.service.executor.impl;

import com.kk.business.quantization.dao.entity.CnM;
import com.kk.business.quantization.model.po.tushare.CnmVo;
import com.kk.business.quantization.model.po.tushare.TushareData;
import com.kk.business.quantization.service.ICnMService;
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
 * 国内人民币总量 按月
 */
@Service("CnmTaskExecutor")
@Slf4j
public class CnmTaskExecutor implements ITaskExecutor {

    @Resource
    public ITushareDataApi tushareDataApi;
    @Resource
    public ICnMService cnMService;


    /**
     * 下载数据
     * @param params
     */
    @Override
    public void executeTask(String params) {
        if(StringUtils.isBlank(params)) return ;
        CnmVo vo = (CnmVo) JsonUtil.parseObject(params,CnmVo.class);
        List<CnM> data = new ArrayList<>();

        TushareData<CnM> res =  tushareDataApi.cnm(vo);
        data.addAll(res.getData());
        //插入db
        cnMService.insertIgnoreBatch(data);
    }


}
