package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.KdjCross;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.KdjCrossListResVo;
import com.kk.business.quantization.model.vobase.req.KdjCrossListReqVo;
/**
 * <p>
 * kdj交叉点 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface KdjCrossMapper extends RootMapper<KdjCross> {
     /**
     * 查询kdj交叉点列表
     */
     Page<KdjCrossListResVo> selectKdjCrossPageList(Page page, KdjCrossListReqVo kdjCrossListReqVo);
}
