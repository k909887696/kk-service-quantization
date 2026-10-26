package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.SyDagProcessTask;
import com.kk.business.quantization.service.ISyDagProcessTaskService;
import com.kk.business.quantization.serviceapi.SyDagProcessTaskServiceApi;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskListReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagProcessTaskListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagProcessTaskResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * DAG执行实例任务 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Service
public class SyDagProcessTaskServiceView   {

    @Resource
    public SyDagProcessTaskServiceApi syDagProcessTaskServiceApi;



}
