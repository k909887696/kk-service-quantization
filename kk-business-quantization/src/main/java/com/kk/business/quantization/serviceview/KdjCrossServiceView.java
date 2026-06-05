package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.KdjCross;
import com.kk.business.quantization.service.IKdjCrossService;
import com.kk.business.quantization.serviceapi.KdjCrossServiceApi;
import com.kk.business.quantization.model.vobase.req.KdjCrossListReqVo;
import com.kk.business.quantization.model.vobase.res.KdjCrossListResVo;
import com.kk.business.quantization.model.vobase.req.KdjCrossAddReqVo;
import com.kk.business.quantization.model.vobase.req.KdjCrossEditReqVo;
import com.kk.business.quantization.model.vobase.res.KdjCrossResVo;
import com.kk.business.quantization.model.vobase.req.KdjCrossDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.KdjCrossDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * kdj交叉点 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-05
 */
@Service
public class KdjCrossServiceView   {

    @Resource
    public KdjCrossServiceApi kdjCrossServiceApi;



}
