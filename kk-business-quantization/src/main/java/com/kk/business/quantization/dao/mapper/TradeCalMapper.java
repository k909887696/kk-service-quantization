package com.kk.business.quantization.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.TradeCal;
import com.kk.business.quantization.model.vo.TradeCalListVo;
import com.kk.common.dao.mapper.RootMapper;
/**
 * <p>
 * 交易日历 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
public interface TradeCalMapper extends RootMapper<TradeCal> {
     /**
     * 查询列表
     */
     Page selectPageList(IPage page, TradeCalListVo tradeCalListVo);
}
