package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.StockFluctuation;
import java.util.List;
import com.kk.business.quantization.model.vobase.req.StockFluctuationListReqVo;
import com.kk.business.quantization.model.vobase.res.StockFluctuationListResVo;
import com.kk.business.quantization.model.vobase.req.StockFluctuationAddReqVo;
import com.kk.business.quantization.model.vobase.req.StockFluctuationEditReqVo;
import com.kk.business.quantization.model.vobase.res.StockFluctuationResVo;
import com.kk.business.quantization.model.vobase.req.StockFluctuationDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.StockFluctuationDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 个股异常波动信息 服务类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface IStockFluctuationService  {

    /**
    * 分批批量插入个股异常波动信息
    * @param list 数据列表
    * @return
    */
    void insertStockFluctuationBatchSomeColumn(List<StockFluctuation> list);
    /**
    * 单条插入个股异常波动信息
    * @param vo 请求参数
    * @return 结果集
    */
    void insertStockFluctuation(StockFluctuationAddReqVo vo);
    /**
    * 更新个股异常波动信息
    * @param vo 请求参数
    * @return 结果集
    */
    int updateStockFluctuation(StockFluctuationEditReqVo vo);
    /**
    * 单条查询个股异常波动信息
    * @param vo 请求参数
    * @return 结果集
    */
    StockFluctuationResVo selectStockFluctuationById(StockFluctuationDetailsReqVo vo);
    /**
    * 删除个股异常波动信息
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteStockFluctuationById(StockFluctuationDeleteReqVo vo);
    /**
    * 分页获取个股异常波动信息结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<StockFluctuationListResVo>  selectStockFluctuationPageList(StockFluctuationListReqVo vo);
}

