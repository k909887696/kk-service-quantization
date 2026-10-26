package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.SyDagNode;
import com.kk.business.quantization.service.ISyDagNodeService;
import com.kk.business.quantization.serviceapi.SyDagNodeServiceApi;
import com.kk.business.quantization.model.vobase.req.SyDagNodeListReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagNodeListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagNodeAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagNodeEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagNodeResVo;
import com.kk.business.quantization.model.vobase.req.SyDagNodeDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagNodeDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * DAG执行节点 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Service
public class SyDagNodeServiceView   {

    @Resource
    public SyDagNodeServiceApi syDagNodeServiceApi;



}
