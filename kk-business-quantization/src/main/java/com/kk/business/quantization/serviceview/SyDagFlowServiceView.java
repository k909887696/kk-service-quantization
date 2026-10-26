package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.SyDagFlow;
import com.kk.business.quantization.service.ISyDagFlowService;
import com.kk.business.quantization.serviceapi.SyDagFlowServiceApi;
import com.kk.business.quantization.model.vobase.req.SyDagFlowListReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagFlowListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagFlowAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagFlowEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagFlowResVo;
import com.kk.business.quantization.model.vobase.req.SyDagFlowDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagFlowDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * DAG执行流 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Service
public class SyDagFlowServiceView   {

    @Resource
    public SyDagFlowServiceApi syDagFlowServiceApi;



}
