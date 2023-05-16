package com.kk.business.quantization.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.ConceptMoneyFlow;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.dto.ConceptMoneyFlowListDto;
import com.kk.business.quantization.model.vo.ConceptMoneyFlowListVo;
/**
 * <p>
 * 概念资金流向 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
public interface ConceptMoneyFlowMapper extends RootMapper<ConceptMoneyFlow> {
     /**
     * 查询列表
     */
     Page selectPageList(IPage page, ConceptMoneyFlowListVo conceptMoneyFlowListVo);
}
