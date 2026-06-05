package com.kk.api.quantization.controller.v;


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
import com.kk.business.quantization.serviceapi.InvokeTypeServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.InvokeTypeListReqVo;
import com.kk.business.quantization.model.vobase.res.InvokeTypeListResVo;
import com.kk.business.quantization.model.vobase.req.InvokeTypeAddReqVo;
import com.kk.business.quantization.model.vobase.req.InvokeTypeEditReqVo;
import com.kk.business.quantization.model.vobase.res.InvokeTypeResVo;
import com.kk.business.quantization.model.vobase.req.InvokeTypeDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.InvokeTypeDeleteReqVo;
/**
 * <p>
 * 系统设置-调度类型 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/VInvokeType",description = "系统设置-调度类型(接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/VInvokeType")
public class VInvokeTypeController {

    @Resource
    public InvokeTypeServiceApi invokeTypeServiceApi;

    @Operation(summary = "获取系统设置-调度类型分页结果集", description = "获取系统设置-调度类型分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getInvokeTypePageList")
    //@SaCheckPermission(value = {"v_menu_select_InvokeType"})
    public ApiResult<PageResult<InvokeTypeListResVo>> getInvokeTypePageList(
            @RequestBody InvokeTypeListReqVo vo)   {
        return new ApiResult<>(invokeTypeServiceApi.selectInvokeTypePageList(vo));
    }
    @Operation(summary = "删除系统设置-调度类型", description = "删除系统设置-调度类型")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteInvokeTypeById")
    //@SaCheckPermission(value={"v_btn_delete_InvokeType"})
    public ApiResult<?> deleteInvokeTypeById(
            @RequestBody InvokeTypeDeleteReqVo vo)   {
        invokeTypeServiceApi.deleteInvokeTypeById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入系统设置-调度类型", description = "插入系统设置-调度类型")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertInvokeType")
    //@SaCheckPermission(value={"v_btn_modify_InvokeType"})
    public ApiResult<?> insertInvokeType(
            @RequestBody InvokeTypeAddReqVo vo)   {
        invokeTypeServiceApi.insertInvokeType(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新系统设置-调度类型", description = "更新系统设置-调度类型")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateInvokeType")
    //@SaCheckPermission(value={"v_btn_modify_InvokeType"})
    public ApiResult<?> updateInvokeType(
            @RequestBody InvokeTypeEditReqVo vo)   {
        invokeTypeServiceApi.updateInvokeType(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询系统设置-调度类型详情", description = "查询系统设置-调度类型详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getInvokeTypeDetails")
    //@SaCheckPermission(value={"inc_menu_select_InvokeType"})
    public ApiResult< InvokeTypeResVo > getInvokeTypeDetails(
            @RequestBody InvokeTypeDetailsReqVo vo)   {
        return new  ApiResult<>(invokeTypeServiceApi.selectInvokeTypeById(vo));
    }



}
