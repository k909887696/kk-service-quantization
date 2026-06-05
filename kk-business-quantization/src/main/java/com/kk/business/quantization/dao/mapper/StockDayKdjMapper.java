package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.StockDayKdj;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.StockDayKdjListResVo;
import com.kk.business.quantization.model.vobase.req.StockDayKdjListReqVo;
/**
 * <p>
 * 个股kdj数据 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface StockDayKdjMapper extends RootMapper<StockDayKdj> {
     /**
     * 查询个股kdj数据列表
     */
     Page<StockDayKdjListResVo> selectStockDayKdjPageList(Page page, StockDayKdjListReqVo stockDayKdjListReqVo);
}
