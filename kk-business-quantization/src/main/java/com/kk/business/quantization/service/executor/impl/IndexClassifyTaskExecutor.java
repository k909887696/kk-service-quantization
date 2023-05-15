package com.kk.business.quantization.service.executor.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kk.business.quantization.dao.entity.IndexClassify;
import com.kk.business.quantization.dao.mapper.IndexClassifyMapper;
import com.kk.business.quantization.model.po.tushare.IndexClassifyVo;
import com.kk.business.quantization.model.po.tushare.TushareData;
import com.kk.business.quantization.service.IIndexClassifyService;
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
 * 概念基本信息任务执行器
 */
@Service("IndexClassifyTaskExecutor")
@Slf4j
public class IndexClassifyTaskExecutor implements ITaskExecutor {

    @Resource
    public ITushareDataApi tushareDataApi;
    @Resource
    public IIndexClassifyService iIndexClassifyService;
    @Resource
    public IndexClassifyMapper indexClassifyMapper;


    /**
     * 下载数据
     * @param params
     */
    @Override
    public void executeTask(String params) {
        if(StringUtils.isBlank(params)) return ;
        IndexClassifyVo vo = (IndexClassifyVo) JsonUtil.parseObject(params,IndexClassifyVo.class);
        List<IndexClassify> data = new ArrayList<>();

        TushareData<IndexClassify> res =  tushareDataApi.indexClassify(vo);
        if("cover".equals(vo.getUpdateType()))
        {
            QueryWrapper<IndexClassify> query = new QueryWrapper<>();
            query.eq("src",vo.getSrc());
            indexClassifyMapper.delete(query);
        }
        data.addAll(res.getData());
        //插入db
        iIndexClassifyService.insertIgnoreBatch(data);
    }


}
