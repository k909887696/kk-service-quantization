package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.IndexWeight;
import com.kk.business.quantization.service.IIndexWeightService;
import com.kk.business.quantization.serviceapi.IndexWeightServiceApi;
import com.kk.business.quantization.model.vobase.req.IndexWeightListReqVo;
import com.kk.business.quantization.model.vobase.res.IndexWeightListResVo;
import com.kk.business.quantization.model.vobase.req.IndexWeightAddReqVo;
import com.kk.business.quantization.model.vobase.req.IndexWeightEditReqVo;
import com.kk.business.quantization.model.vobase.res.IndexWeightResVo;
import com.kk.business.quantization.model.vobase.req.IndexWeightDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.IndexWeightDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 指数成分权重 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-05
 */
@Service
public class IndexWeightServiceView   {

    @Resource
    public IndexWeightServiceApi indexWeightServiceApi;



}
