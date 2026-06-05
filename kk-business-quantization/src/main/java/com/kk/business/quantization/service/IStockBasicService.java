package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.StockBasic;
import java.util.List;

import com.kk.business.quantization.model.vo.StockBasicList4InnVo;
import com.kk.business.quantization.model.vobase.req.StockBasicListReqVo;
import com.kk.business.quantization.model.vobase.res.StockBasicListResVo;
import com.kk.business.quantization.model.vobase.req.StockBasicAddReqVo;
import com.kk.business.quantization.model.vobase.req.StockBasicEditReqVo;
import com.kk.business.quantization.model.vobase.res.StockBasicResVo;
import com.kk.business.quantization.model.vobase.req.StockBasicDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.StockBasicDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 个股基本信息 服务类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface IStockBasicService  {

    /**
    * 分批批量插入个股基本信息
    * @param list 数据列表
    * @return
    */
    void insertStockBasicBatchSomeColumn(List<StockBasic> list);
    /**
    * 单条插入个股基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    void insertStockBasic(StockBasicAddReqVo vo);
    /**
    * 更新个股基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    int updateStockBasic(StockBasicEditReqVo vo);
    /**
    * 单条查询个股基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    StockBasicResVo selectStockBasicById(StockBasicDetailsReqVo vo);
    /**
    * 删除个股基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteStockBasicById(StockBasicDeleteReqVo vo);
    /**
    * 分页获取个股基本信息结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<StockBasicListResVo>  selectStockBasicPageList(StockBasicListReqVo vo);
    /**
     * 分页获取结果集
     * @param vo 请求参数
     * @return 结果集
     */
    PageResult<StockBasic> getStockBasicPageResult(StockBasicList4InnVo vo);
}

