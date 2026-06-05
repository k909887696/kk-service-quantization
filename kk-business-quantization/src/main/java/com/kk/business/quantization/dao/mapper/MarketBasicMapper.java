package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.MarketBasic;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.MarketBasicListResVo;
import com.kk.business.quantization.model.vobase.req.MarketBasicListReqVo;
/**
 * <p>
 * 市场基本信息 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface MarketBasicMapper extends RootMapper<MarketBasic> {
     /**
     * 查询市场基本信息列表
     */
     Page<MarketBasicListResVo> selectMarketBasicPageList(Page page, MarketBasicListReqVo marketBasicListReqVo);
}
