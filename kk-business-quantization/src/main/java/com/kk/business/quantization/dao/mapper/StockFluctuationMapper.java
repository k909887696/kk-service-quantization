package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.StockFluctuation;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.StockFluctuationListResVo;
import com.kk.business.quantization.model.vobase.req.StockFluctuationListReqVo;
/**
 * <p>
 * 个股异常波动信息 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface StockFluctuationMapper extends RootMapper<StockFluctuation> {
     /**
     * 查询个股异常波动信息列表
     */
     Page<StockFluctuationListResVo> selectStockFluctuationPageList(Page page, StockFluctuationListReqVo stockFluctuationListReqVo);
}
