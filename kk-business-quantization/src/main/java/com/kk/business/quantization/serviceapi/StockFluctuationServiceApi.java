package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.StockFluctuation;
import com.kk.business.quantization.service.IStockFluctuationService;
import com.kk.business.quantization.model.vobase.req.StockFluctuationListReqVo;
import com.kk.business.quantization.model.vobase.res.StockFluctuationListResVo;
import com.kk.business.quantization.model.vobase.req.StockFluctuationAddReqVo;
import com.kk.business.quantization.model.vobase.req.StockFluctuationEditReqVo;
import com.kk.business.quantization.model.vobase.res.StockFluctuationResVo;
import com.kk.business.quantization.model.vobase.req.StockFluctuationDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.StockFluctuationDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * 个股异常波动信息 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class StockFluctuationServiceApi   {

    @Resource
    public IStockFluctuationService stockFluctuationService;

    /**
    * 分批批量插入个股异常波动信息
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertStockFluctuationBatchSomeColumn(List<StockFluctuation> list)
    {
        stockFluctuationService.insertStockFluctuationBatchSomeColumn(list);
    }
    /**
    * 单条插入个股异常波动信息
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertStockFluctuation(StockFluctuationAddReqVo vo)
    {
        stockFluctuationService.insertStockFluctuation(vo);
    }
    /**
    * 更新个股异常波动信息
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateStockFluctuation(StockFluctuationEditReqVo vo)
    {
        return stockFluctuationService.updateStockFluctuation(vo);
    }
    /**
    * 单条查询个股异常波动信息
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public StockFluctuationResVo selectStockFluctuationById(StockFluctuationDetailsReqVo vo)
    {
        return stockFluctuationService.selectStockFluctuationById(vo);
    }
    /**
    * 删除个股异常波动信息
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteStockFluctuationById(StockFluctuationDeleteReqVo vo)
    {
        return stockFluctuationService.deleteStockFluctuationById(vo);
    }
    /**
    * 分页获取个股异常波动信息结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<StockFluctuationListResVo>  selectStockFluctuationPageList(StockFluctuationListReqVo vo){
        return stockFluctuationService.selectStockFluctuationPageList(vo);
    }



}
