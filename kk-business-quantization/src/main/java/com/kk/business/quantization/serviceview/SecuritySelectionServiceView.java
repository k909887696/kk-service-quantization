package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.SecuritySelection;
import com.kk.business.quantization.service.ISecuritySelectionService;
import com.kk.business.quantization.serviceapi.SecuritySelectionServiceApi;
import com.kk.business.quantization.model.vobase.req.SecuritySelectionListReqVo;
import com.kk.business.quantization.model.vobase.res.SecuritySelectionListResVo;
import com.kk.business.quantization.model.vobase.req.SecuritySelectionAddReqVo;
import com.kk.business.quantization.model.vobase.req.SecuritySelectionEditReqVo;
import com.kk.business.quantization.model.vobase.res.SecuritySelectionResVo;
import com.kk.business.quantization.model.vobase.req.SecuritySelectionDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SecuritySelectionDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 个人自选股 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-05
 */
@Service
public class SecuritySelectionServiceView   {

    @Resource
    public SecuritySelectionServiceApi securitySelectionServiceApi;



}
