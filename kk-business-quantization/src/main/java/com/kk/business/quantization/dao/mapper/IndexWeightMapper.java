package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.IndexWeight;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.IndexWeightListResVo;
import com.kk.business.quantization.model.vobase.req.IndexWeightListReqVo;
/**
 * <p>
 * 指数成分权重 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface IndexWeightMapper extends RootMapper<IndexWeight> {
     /**
     * 查询指数成分权重列表
     */
     Page<IndexWeightListResVo> selectIndexWeightPageList(Page page, IndexWeightListReqVo indexWeightListReqVo);
}
