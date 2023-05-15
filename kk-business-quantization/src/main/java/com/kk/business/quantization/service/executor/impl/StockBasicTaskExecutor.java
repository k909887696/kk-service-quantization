package com.kk.business.quantization.service.executor.impl;

import com.kk.business.quantization.dao.entity.StockBasic;
import com.kk.business.quantization.dao.mapper.StockBasicMapper;
import com.kk.business.quantization.model.po.tushare.StockBasicVo;
import com.kk.business.quantization.model.po.tushare.TushareData;
import com.kk.business.quantization.service.IStockBasicService;
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
 * 个股基本信息任务执行器
 */
@Service("StockBasicTaskExecutor")
@Slf4j
public class StockBasicTaskExecutor implements ITaskExecutor {

    @Resource
    public ITushareDataApi tushareDataApi;
    @Resource
    public IStockBasicService stockBasicService;

    @Resource
    public StockBasicMapper stockBasicMapper;

    /**
     * 下载数据
     * @param params
     */
    @Override
    public void executeTask(String params) {
        if(StringUtils.isBlank(params)) return ;
        StockBasicVo vo = (StockBasicVo) JsonUtil.parseObject(params,StockBasicVo.class);
        List<StockBasic> data = new ArrayList<>();

        TushareData<StockBasic> res =  tushareDataApi.stockBasic(vo);
        data.addAll(res.getData());
        if(vo.getLimit()<=0)
        {
            vo.setLimit(res.getData().size());
        }
        int i=0;
        while (res.getHasMore() && i<=10)
        {
            i++;
            vo.setOffset(i*vo.getLimit()+1);
            res = tushareDataApi.stockBasic(vo);
            data.addAll(res.getData());
        }
        if("cover".equals(vo.getUpdateType()))//覆盖模式
        {
            stockBasicMapper.truncateTable();
        }
        //插入db
        stockBasicService.insertIgnoreBatch(data);
    }


}
