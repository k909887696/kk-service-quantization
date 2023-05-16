package com.kk.business.quantization.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.Concept;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.dto.ConceptListDto;
import com.kk.business.quantization.model.vo.ConceptListVo;
/**
 * <p>
 * 概念分类 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
public interface ConceptMapper extends RootMapper<Concept> {
     /**
     * 查询列表
     */
     Page selectPageList(IPage page, ConceptListVo conceptListVo);
}
