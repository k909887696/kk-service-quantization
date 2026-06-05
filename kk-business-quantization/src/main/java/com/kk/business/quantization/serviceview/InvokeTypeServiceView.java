package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.InvokeType;
import com.kk.business.quantization.service.IInvokeTypeService;
import com.kk.business.quantization.serviceapi.InvokeTypeServiceApi;
import com.kk.business.quantization.model.vobase.req.InvokeTypeListReqVo;
import com.kk.business.quantization.model.vobase.res.InvokeTypeListResVo;
import com.kk.business.quantization.model.vobase.req.InvokeTypeAddReqVo;
import com.kk.business.quantization.model.vobase.req.InvokeTypeEditReqVo;
import com.kk.business.quantization.model.vobase.res.InvokeTypeResVo;
import com.kk.business.quantization.model.vobase.req.InvokeTypeDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.InvokeTypeDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 系统设置-调度类型 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-05
 */
@Service
public class InvokeTypeServiceView   {

    @Resource
    public InvokeTypeServiceApi invokeTypeServiceApi;



}
