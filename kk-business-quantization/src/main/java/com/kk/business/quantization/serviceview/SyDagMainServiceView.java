package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.SyDagMain;
import com.kk.business.quantization.service.ISyDagMainService;
import com.kk.business.quantization.serviceapi.SyDagMainServiceApi;
import com.kk.business.quantization.model.vobase.req.SyDagMainListReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagMainListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagMainAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagMainEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagMainResVo;
import com.kk.business.quantization.model.vobase.req.SyDagMainDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagMainDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * DAG主表 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Service
public class SyDagMainServiceView   {

    @Resource
    public SyDagMainServiceApi syDagMainServiceApi;



}
