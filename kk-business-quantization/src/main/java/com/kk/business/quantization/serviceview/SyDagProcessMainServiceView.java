package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.SyDagProcessMain;
import com.kk.business.quantization.service.ISyDagProcessMainService;
import com.kk.business.quantization.serviceapi.SyDagProcessMainServiceApi;
import com.kk.business.quantization.model.vobase.req.SyDagProcessMainListReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagProcessMainListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessMainAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessMainEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagProcessMainResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessMainDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessMainDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * DAG执行实例主表 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Service
public class SyDagProcessMainServiceView   {

    @Resource
    public SyDagProcessMainServiceApi syDagProcessMainServiceApi;



}
