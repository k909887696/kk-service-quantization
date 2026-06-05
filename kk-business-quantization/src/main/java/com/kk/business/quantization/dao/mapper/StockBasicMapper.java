package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.StockBasic;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.StockBasicListResVo;
import com.kk.business.quantization.model.vobase.req.StockBasicListReqVo;
/**
 * <p>
 * 个股基本信息 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface StockBasicMapper extends RootMapper<StockBasic> {
     /**
     * 查询个股基本信息列表
     */
     Page<StockBasicListResVo> selectStockBasicPageList(Page page, StockBasicListReqVo stockBasicListReqVo);
}
