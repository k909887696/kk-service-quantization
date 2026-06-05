package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.CnM;
import com.kk.business.quantization.service.ICnMService;
import com.kk.business.quantization.serviceapi.CnMServiceApi;
import com.kk.business.quantization.model.vobase.req.CnMListReqVo;
import com.kk.business.quantization.model.vobase.res.CnMListResVo;
import com.kk.business.quantization.model.vobase.req.CnMAddReqVo;
import com.kk.business.quantization.model.vobase.req.CnMEditReqVo;
import com.kk.business.quantization.model.vobase.res.CnMResVo;
import com.kk.business.quantization.model.vobase.req.CnMDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.CnMDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 人民币货币总量对象 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-05
 */
@Service
public class CnMServiceView   {

    @Resource
    public CnMServiceApi cnMServiceApi;



}
