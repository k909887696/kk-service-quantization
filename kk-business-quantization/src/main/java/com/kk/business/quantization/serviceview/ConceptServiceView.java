package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.Concept;
import com.kk.business.quantization.service.IConceptService;
import com.kk.business.quantization.serviceapi.ConceptServiceApi;
import com.kk.business.quantization.model.vobase.req.ConceptListReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptListResVo;
import com.kk.business.quantization.model.vobase.req.ConceptAddReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptEditReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptResVo;
import com.kk.business.quantization.model.vobase.req.ConceptDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 概念分类 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-05
 */
@Service
public class ConceptServiceView   {

    @Resource
    public ConceptServiceApi conceptServiceApi;



}
