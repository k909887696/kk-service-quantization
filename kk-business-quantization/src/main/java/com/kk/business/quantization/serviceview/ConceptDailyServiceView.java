package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.ConceptDaily;
import com.kk.business.quantization.service.IConceptDailyService;
import com.kk.business.quantization.serviceapi.ConceptDailyServiceApi;
import com.kk.business.quantization.model.vobase.req.ConceptDailyListReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptDailyListResVo;
import com.kk.business.quantization.model.vobase.req.ConceptDailyAddReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptDailyEditReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptDailyResVo;
import com.kk.business.quantization.model.vobase.req.ConceptDailyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptDailyDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 概念日线行情 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-05
 */
@Service
public class ConceptDailyServiceView   {

    @Resource
    public ConceptDailyServiceApi conceptDailyServiceApi;



}
