package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.SyUser;
import com.kk.business.quantization.service.ISyUserService;
import com.kk.business.quantization.serviceapi.SyUserServiceApi;
import com.kk.business.quantization.model.vobase.req.SyUserListReqVo;
import com.kk.business.quantization.model.vobase.res.SyUserListResVo;
import com.kk.business.quantization.model.vobase.req.SyUserAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyUserEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyUserResVo;
import com.kk.business.quantization.model.vobase.req.SyUserDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyUserDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 用户信息 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-08
 */
@Service
public class SyUserServiceView   {

    @Resource
    public SyUserServiceApi syUserServiceApi;



}
