package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.CollectionTask;
import com.kk.business.quantization.service.ICollectionTaskService;
import com.kk.business.quantization.serviceapi.CollectionTaskServiceApi;
import com.kk.business.quantization.model.vobase.req.CollectionTaskListReqVo;
import com.kk.business.quantization.model.vobase.res.CollectionTaskListResVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskAddReqVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskEditReqVo;
import com.kk.business.quantization.model.vobase.res.CollectionTaskResVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 系统设置-数据任务 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-05
 */
@Service
public class CollectionTaskServiceView   {

    @Resource
    public CollectionTaskServiceApi collectionTaskServiceApi;



}
