package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.TradeCal;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.TradeCalListResVo;
import com.kk.business.quantization.model.vobase.req.TradeCalListReqVo;
/**
 * <p>
 * 交易日历 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface TradeCalMapper extends RootMapper<TradeCal> {
     /**
     * 查询交易日历列表
     */
     Page<TradeCalListResVo> selectTradeCalPageList(Page page, TradeCalListReqVo tradeCalListReqVo);
}
