package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.ConceptMoneyFlow;
import com.kk.business.quantization.service.IConceptMoneyFlowService;
import com.kk.business.quantization.serviceapi.ConceptMoneyFlowServiceApi;
import com.kk.business.quantization.model.vobase.req.ConceptMoneyFlowListReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptMoneyFlowListResVo;
import com.kk.business.quantization.model.vobase.req.ConceptMoneyFlowAddReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptMoneyFlowEditReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptMoneyFlowResVo;
import com.kk.business.quantization.model.vobase.req.ConceptMoneyFlowDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptMoneyFlowDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 概念资金流向 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-05
 */
@Service
public class ConceptMoneyFlowServiceView   {

    @Resource
    public ConceptMoneyFlowServiceApi conceptMoneyFlowServiceApi;



}
