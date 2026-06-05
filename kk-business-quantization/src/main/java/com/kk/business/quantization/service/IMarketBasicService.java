package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.MarketBasic;
import java.util.List;
import com.kk.business.quantization.model.vobase.req.MarketBasicListReqVo;
import com.kk.business.quantization.model.vobase.res.MarketBasicListResVo;
import com.kk.business.quantization.model.vobase.req.MarketBasicAddReqVo;
import com.kk.business.quantization.model.vobase.req.MarketBasicEditReqVo;
import com.kk.business.quantization.model.vobase.res.MarketBasicResVo;
import com.kk.business.quantization.model.vobase.req.MarketBasicDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.MarketBasicDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 市场基本信息 服务类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface IMarketBasicService  {

    /**
    * 分批批量插入市场基本信息
    * @param list 数据列表
    * @return
    */
    void insertMarketBasicBatchSomeColumn(List<MarketBasic> list);
    /**
    * 单条插入市场基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    void insertMarketBasic(MarketBasicAddReqVo vo);
    /**
    * 更新市场基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    int updateMarketBasic(MarketBasicEditReqVo vo);
    /**
    * 单条查询市场基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    MarketBasicResVo selectMarketBasicById(MarketBasicDetailsReqVo vo);
    /**
    * 删除市场基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteMarketBasicById(MarketBasicDeleteReqVo vo);
    /**
    * 分页获取市场基本信息结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<MarketBasicListResVo>  selectMarketBasicPageList(MarketBasicListReqVo vo);
}

