package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.StockDayKdj;
import java.util.List;
import com.kk.business.quantization.model.vobase.req.StockDayKdjListReqVo;
import com.kk.business.quantization.model.vobase.res.StockDayKdjListResVo;
import com.kk.business.quantization.model.vobase.req.StockDayKdjAddReqVo;
import com.kk.business.quantization.model.vobase.req.StockDayKdjEditReqVo;
import com.kk.business.quantization.model.vobase.res.StockDayKdjResVo;
import com.kk.business.quantization.model.vobase.req.StockDayKdjDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.StockDayKdjDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 个股kdj数据 服务类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface IStockDayKdjService  {

    /**
    * 分批批量插入个股kdj数据
    * @param list 数据列表
    * @return
    */
    void insertStockDayKdjBatchSomeColumn(List<StockDayKdj> list);
    /**
    * 单条插入个股kdj数据
    * @param vo 请求参数
    * @return 结果集
    */
    void insertStockDayKdj(StockDayKdjAddReqVo vo);
    /**
    * 更新个股kdj数据
    * @param vo 请求参数
    * @return 结果集
    */
    int updateStockDayKdj(StockDayKdjEditReqVo vo);
    /**
    * 单条查询个股kdj数据
    * @param vo 请求参数
    * @return 结果集
    */
    StockDayKdjResVo selectStockDayKdjById(StockDayKdjDetailsReqVo vo);
    /**
    * 删除个股kdj数据
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteStockDayKdjById(StockDayKdjDeleteReqVo vo);
    /**
    * 分页获取个股kdj数据结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<StockDayKdjListResVo>  selectStockDayKdjPageList(StockDayKdjListReqVo vo);
}

