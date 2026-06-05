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
import com.kk.business.quantization.serviceapi.SecuritySelectionServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.SecuritySelectionListReqVo;
import com.kk.business.quantization.model.vobase.res.SecuritySelectionListResVo;
import com.kk.business.quantization.model.vobase.req.SecuritySelectionAddReqVo;
import com.kk.business.quantization.model.vobase.req.SecuritySelectionEditReqVo;
import com.kk.business.quantization.model.vobase.res.SecuritySelectionResVo;
import com.kk.business.quantization.model.vobase.req.SecuritySelectionDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SecuritySelectionDeleteReqVo;
/**
 * <p>
 * 个人自选股 内部接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnSecuritySelection",description = "个人自选股(内部接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnSecuritySelection")
public class InnSecuritySelectionController {

    @Resource
    public SecuritySelectionServiceApi securitySelectionServiceApi;

    @Operation(summary = "获取个人自选股分页结果集", description = "获取个人自选股分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getSecuritySelectionPageList")
    //@SaCheckPermission(value = {"v_menu_select_SecuritySelection"})
    public ApiResult<PageResult<SecuritySelectionListResVo>> getSecuritySelectionPageList(
            @RequestBody SecuritySelectionListReqVo vo)   {
        return new ApiResult<>(securitySelectionServiceApi.selectSecuritySelectionPageList(vo));
    }
    @Operation(summary = "删除个人自选股", description = "删除个人自选股")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteSecuritySelectionById")
    //@SaCheckPermission(value={"v_btn_delete_SecuritySelection"})
    public ApiResult<?> deleteSecuritySelectionById(
            @RequestBody SecuritySelectionDeleteReqVo vo)   {
        securitySelectionServiceApi.deleteSecuritySelectionById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入个人自选股", description = "插入个人自选股")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertSecuritySelection")
    //@SaCheckPermission(value={"v_btn_modify_SecuritySelection"})
    public ApiResult<?> insertSecuritySelection(
            @RequestBody SecuritySelectionAddReqVo vo)   {
        securitySelectionServiceApi.insertSecuritySelection(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新个人自选股", description = "更新个人自选股")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateSecuritySelection")
    //@SaCheckPermission(value={"v_btn_modify_SecuritySelection"})
    public ApiResult<?> updateSecuritySelection(
            @RequestBody SecuritySelectionEditReqVo vo)   {
        securitySelectionServiceApi.updateSecuritySelection(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询个人自选股详情", description = "查询个人自选股详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getSecuritySelectionDetails")
    //@SaCheckPermission(value={"inc_menu_select_SecuritySelection"})
    public ApiResult< SecuritySelectionResVo > getSecuritySelectionDetails(
            @RequestBody SecuritySelectionDetailsReqVo vo)   {
        return new  ApiResult<>(securitySelectionServiceApi.selectSecuritySelectionById(vo));
    }



}
