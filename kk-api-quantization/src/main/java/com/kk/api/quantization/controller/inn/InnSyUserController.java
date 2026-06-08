package com.kk.api.quantization.controller.inn;


import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.Resource;
import com.kk.business.quantization.serviceapi.SyUserServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.SyUserListReqVo;
import com.kk.business.quantization.model.vobase.res.SyUserListResVo;
import com.kk.business.quantization.model.vobase.req.SyUserAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyUserEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyUserResVo;
import com.kk.business.quantization.model.vobase.req.SyUserDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyUserDeleteReqVo;
/**
 * <p>
 * 用户信息 内部接口
 * </p>
 *
 * @author kk
 * @since 2026-06-08
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnSyUser",description = "用户信息(内部接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnSyUser")
public class InnSyUserController {

    @Resource
    public SyUserServiceApi syUserServiceApi;

    @Operation(summary = "获取用户信息分页结果集", description = "获取用户信息分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getSyUserPageList")
    //@SaCheckPermission(value = {"v_menu_select_SyUser"})
    public ApiResult<PageResult<SyUserListResVo>> getSyUserPageList(
            @RequestBody SyUserListReqVo vo)   {
        return new ApiResult<>(syUserServiceApi.selectSyUserPageList(vo));
    }
    @Operation(summary = "删除用户信息", description = "删除用户信息")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteSyUserById")
    //@SaCheckPermission(value={"v_btn_delete_SyUser"})
    public ApiResult<?> deleteSyUserById(
            @RequestBody SyUserDeleteReqVo vo)   {
        syUserServiceApi.deleteSyUserById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入用户信息", description = "插入用户信息")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertSyUser")
    //@SaCheckPermission(value={"v_btn_modify_SyUser"})
    public ApiResult<?> insertSyUser(
            @RequestBody SyUserAddReqVo vo)   {
        syUserServiceApi.insertSyUser(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新用户信息", description = "更新用户信息")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateSyUser")
    //@SaCheckPermission(value={"v_btn_modify_SyUser"})
    public ApiResult<?> updateSyUser(
            @RequestBody SyUserEditReqVo vo)   {
        syUserServiceApi.updateSyUser(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询用户信息详情", description = "查询用户信息详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getSyUserDetails")
    //@SaCheckPermission(value={"inc_menu_select_SyUser"})
    public ApiResult< SyUserResVo > getSyUserDetails(
            @RequestBody SyUserDetailsReqVo vo)   {
        return new  ApiResult<>(syUserServiceApi.selectSyUserById(vo));
    }



}
