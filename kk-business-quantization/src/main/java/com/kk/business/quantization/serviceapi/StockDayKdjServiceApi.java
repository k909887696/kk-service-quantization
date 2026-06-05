package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.StockDayKdj;
import com.kk.business.quantization.service.IStockDayKdjService;
import com.kk.business.quantization.model.vobase.req.StockDayKdjListReqVo;
import com.kk.business.quantization.model.vobase.res.StockDayKdjListResVo;
import com.kk.business.quantization.model.vobase.req.StockDayKdjAddReqVo;
import com.kk.business.quantization.model.vobase.req.StockDayKdjEditReqVo;
import com.kk.business.quantization.model.vobase.res.StockDayKdjResVo;
import com.kk.business.quantization.model.vobase.req.StockDayKdjDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.StockDayKdjDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * 个股kdj数据 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class StockDayKdjServiceApi   {

    @Resource
    public IStockDayKdjService stockDayKdjService;

    /**
    * 分批批量插入个股kdj数据
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertStockDayKdjBatchSomeColumn(List<StockDayKdj> list)
    {
        stockDayKdjService.insertStockDayKdjBatchSomeColumn(list);
    }
    /**
    * 单条插入个股kdj数据
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertStockDayKdj(StockDayKdjAddReqVo vo)
    {
        stockDayKdjService.insertStockDayKdj(vo);
    }
    /**
    * 更新个股kdj数据
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateStockDayKdj(StockDayKdjEditReqVo vo)
    {
        return stockDayKdjService.updateStockDayKdj(vo);
    }
    /**
    * 单条查询个股kdj数据
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public StockDayKdjResVo selectStockDayKdjById(StockDayKdjDetailsReqVo vo)
    {
        return stockDayKdjService.selectStockDayKdjById(vo);
    }
    /**
    * 删除个股kdj数据
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteStockDayKdjById(StockDayKdjDeleteReqVo vo)
    {
        return stockDayKdjService.deleteStockDayKdjById(vo);
    }
    /**
    * 分页获取个股kdj数据结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<StockDayKdjListResVo>  selectStockDayKdjPageList(StockDayKdjListReqVo vo){
        return stockDayKdjService.selectStockDayKdjPageList(vo);
    }



}
