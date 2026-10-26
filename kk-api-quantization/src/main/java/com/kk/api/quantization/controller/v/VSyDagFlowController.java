package com.kk.business.quantization.controller.v;


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
import com.kk.business.quantization.serviceapi.SyDagFlowServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.SyDagFlowListReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagFlowListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagFlowAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagFlowEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagFlowResVo;
import com.kk.business.quantization.model.vobase.req.SyDagFlowDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagFlowDeleteReqVo;
/**
 * <p>
 *  接口
 * </p>
 *
 * @author kk
 * @since 2026-10-22
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/VSyDagFlow",description = "(接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/VSyDagFlow")
public class VSyDagFlowController {

    @Resource
    public SyDagFlowServiceApi syDagFlowServiceApi;

    @Operation(summary = "获取分页结果集", description = "获取分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getSyDagFlowPageList")
    //@SaCheckPermission(value = {"v_menu_select_SyDagFlow"})
    public ApiResult<PageResult<SyDagFlowListResVo>> getSyDagFlowPageList(
            @RequestBody SyDagFlowListReqVo vo)   {
        return new ApiResult<>(syDagFlowServiceApi.selectSyDagFlowPageList(vo));
    }
    @Operation(summary = "删除", description = "删除")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteSyDagFlowById")
    //@SaCheckPermission(value={"v_btn_delete_SyDagFlow"})
    public ApiResult<?> deleteSyDagFlowById(
            @RequestBody SyDagFlowDeleteReqVo vo)   {
        syDagFlowServiceApi.deleteSyDagFlowById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入", description = "插入")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertSyDagFlow")
    //@SaCheckPermission(value={"v_btn_modify_SyDagFlow"})
    public ApiResult<?> insertSyDagFlow(
            @RequestBody SyDagFlowAddReqVo vo)   {
        syDagFlowServiceApi.insertSyDagFlow(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新", description = "更新")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateSyDagFlow")
    //@SaCheckPermission(value={"v_btn_modify_SyDagFlow"})
    public ApiResult<?> updateSyDagFlow(
            @RequestBody SyDagFlowEditReqVo vo)   {
        syDagFlowServiceApi.updateSyDagFlow(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询详情", description = "查询详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getSyDagFlowDetails")
    //@SaCheckPermission(value={"inc_menu_select_SyDagFlow"})
    public ApiResult< SyDagFlowResVo > getSyDagFlowDetails(
            @RequestBody SyDagFlowDetailsReqVo vo)   {
        return new  ApiResult<>(syDagFlowServiceApi.selectSyDagFlowById(vo));
    }



}
