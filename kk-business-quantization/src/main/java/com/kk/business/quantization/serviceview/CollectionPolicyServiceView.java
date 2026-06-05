package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.CollectionPolicy;
import com.kk.business.quantization.service.ICollectionPolicyService;
import com.kk.business.quantization.serviceapi.CollectionPolicyServiceApi;
import com.kk.business.quantization.model.vobase.req.CollectionPolicyListReqVo;
import com.kk.business.quantization.model.vobase.res.CollectionPolicyListResVo;
import com.kk.business.quantization.model.vobase.req.CollectionPolicyAddReqVo;
import com.kk.business.quantization.model.vobase.req.CollectionPolicyEditReqVo;
import com.kk.business.quantization.model.vobase.res.CollectionPolicyResVo;
import com.kk.business.quantization.model.vobase.req.CollectionPolicyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.CollectionPolicyDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 系统设置-数据策略 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-05
 */
@Service
public class CollectionPolicyServiceView   {

    @Resource
    public CollectionPolicyServiceApi collectionPolicyServiceApi;



}
