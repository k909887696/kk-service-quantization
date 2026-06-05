package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.IndexBasic;
import com.kk.business.quantization.service.IIndexBasicService;
import com.kk.business.quantization.serviceapi.IndexBasicServiceApi;
import com.kk.business.quantization.model.vobase.req.IndexBasicListReqVo;
import com.kk.business.quantization.model.vobase.res.IndexBasicListResVo;
import com.kk.business.quantization.model.vobase.req.IndexBasicAddReqVo;
import com.kk.business.quantization.model.vobase.req.IndexBasicEditReqVo;
import com.kk.business.quantization.model.vobase.res.IndexBasicResVo;
import com.kk.business.quantization.model.vobase.req.IndexBasicDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.IndexBasicDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 指数基本信息 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-05
 */
@Service
public class IndexBasicServiceView   {

    @Resource
    public IndexBasicServiceApi indexBasicServiceApi;



}
