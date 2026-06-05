package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.StockBasic;
import com.kk.business.quantization.service.IStockBasicService;
import com.kk.business.quantization.model.vobase.req.StockBasicListReqVo;
import com.kk.business.quantization.model.vobase.res.StockBasicListResVo;
import com.kk.business.quantization.model.vobase.req.StockBasicAddReqVo;
import com.kk.business.quantization.model.vobase.req.StockBasicEditReqVo;
import com.kk.business.quantization.model.vobase.res.StockBasicResVo;
import com.kk.business.quantization.model.vobase.req.StockBasicDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.StockBasicDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * 个股基本信息 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class StockBasicServiceApi   {

    @Resource
    public IStockBasicService stockBasicService;

    /**
    * 分批批量插入个股基本信息
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertStockBasicBatchSomeColumn(List<StockBasic> list)
    {
        stockBasicService.insertStockBasicBatchSomeColumn(list);
    }
    /**
    * 单条插入个股基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertStockBasic(StockBasicAddReqVo vo)
    {
        stockBasicService.insertStockBasic(vo);
    }
    /**
    * 更新个股基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateStockBasic(StockBasicEditReqVo vo)
    {
        return stockBasicService.updateStockBasic(vo);
    }
    /**
    * 单条查询个股基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public StockBasicResVo selectStockBasicById(StockBasicDetailsReqVo vo)
    {
        return stockBasicService.selectStockBasicById(vo);
    }
    /**
    * 删除个股基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteStockBasicById(StockBasicDeleteReqVo vo)
    {
        return stockBasicService.deleteStockBasicById(vo);
    }
    /**
    * 分页获取个股基本信息结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<StockBasicListResVo>  selectStockBasicPageList(StockBasicListReqVo vo){
        return stockBasicService.selectStockBasicPageList(vo);
    }



}
