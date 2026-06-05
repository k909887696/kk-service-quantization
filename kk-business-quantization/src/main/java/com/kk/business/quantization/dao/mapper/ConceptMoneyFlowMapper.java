package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.ConceptMoneyFlow;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.ConceptMoneyFlowListResVo;
import com.kk.business.quantization.model.vobase.req.ConceptMoneyFlowListReqVo;
/**
 * <p>
 * 概念资金流向 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface ConceptMoneyFlowMapper extends RootMapper<ConceptMoneyFlow> {
     /**
     * 查询概念资金流向列表
     */
     Page<ConceptMoneyFlowListResVo> selectConceptMoneyFlowPageList(Page page, ConceptMoneyFlowListReqVo conceptMoneyFlowListReqVo);
}
