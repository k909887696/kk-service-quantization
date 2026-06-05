package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.ConceptDetail;
import com.kk.business.quantization.service.IConceptDetailService;
import com.kk.business.quantization.serviceapi.ConceptDetailServiceApi;
import com.kk.business.quantization.model.vobase.req.ConceptDetailListReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptDetailListResVo;
import com.kk.business.quantization.model.vobase.req.ConceptDetailAddReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptDetailEditReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptDetailResVo;
import com.kk.business.quantization.model.vobase.req.ConceptDetailDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptDetailDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 概念明细 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-05
 */
@Service
public class ConceptDetailServiceView   {

    @Resource
    public ConceptDetailServiceApi conceptDetailServiceApi;



}
