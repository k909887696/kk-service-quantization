package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.SyDagProcessTaskHis;
import com.kk.business.quantization.service.ISyDagProcessTaskHisService;
import com.kk.business.quantization.serviceapi.SyDagProcessTaskHisServiceApi;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskHisListReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagProcessTaskHisListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskHisAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskHisEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagProcessTaskHisResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskHisDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskHisDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * DAG执行实例历史任务 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Service
public class SyDagProcessTaskHisServiceView   {

    @Resource
    public SyDagProcessTaskHisServiceApi syDagProcessTaskHisServiceApi;



}
