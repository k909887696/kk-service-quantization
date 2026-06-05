package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.Concept;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.ConceptListResVo;
import com.kk.business.quantization.model.vobase.req.ConceptListReqVo;
/**
 * <p>
 * 概念分类 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface ConceptMapper extends RootMapper<Concept> {
     /**
     * 查询概念分类列表
     */
     Page<ConceptListResVo> selectConceptPageList(Page page, ConceptListReqVo conceptListReqVo);
}
