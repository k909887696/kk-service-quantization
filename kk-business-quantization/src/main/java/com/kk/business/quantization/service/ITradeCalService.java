package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.TradeCal;
import java.util.List;
import com.kk.business.quantization.model.vobase.req.TradeCalListReqVo;
import com.kk.business.quantization.model.vobase.res.TradeCalListResVo;
import com.kk.business.quantization.model.vobase.req.TradeCalAddReqVo;
import com.kk.business.quantization.model.vobase.req.TradeCalEditReqVo;
import com.kk.business.quantization.model.vobase.res.TradeCalResVo;
import com.kk.business.quantization.model.vobase.req.TradeCalDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.TradeCalDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 交易日历 服务类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface ITradeCalService  {

    /**
    * 分批批量插入交易日历
    * @param list 数据列表
    * @return
    */
    void insertTradeCalBatchSomeColumn(List<TradeCal> list);
    /**
    * 单条插入交易日历
    * @param vo 请求参数
    * @return 结果集
    */
    void insertTradeCal(TradeCalAddReqVo vo);
    /**
    * 更新交易日历
    * @param vo 请求参数
    * @return 结果集
    */
    int updateTradeCal(TradeCalEditReqVo vo);
    /**
    * 单条查询交易日历
    * @param vo 请求参数
    * @return 结果集
    */
    TradeCalResVo selectTradeCalById(TradeCalDetailsReqVo vo);
    /**
    * 删除交易日历
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteTradeCalById(TradeCalDeleteReqVo vo);
    /**
    * 分页获取交易日历结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<TradeCalListResVo>  selectTradeCalPageList(TradeCalListReqVo vo);

    /**
     * 根据日期获取日期最近以往开市日期
     * @param date 日期
     * @param limit 天数
     * @param order 默认asc 升序，desc 降序
     * @return
     */
    TradeCal getRecentlyOpenByDay(String date,int limit,String order);
}

