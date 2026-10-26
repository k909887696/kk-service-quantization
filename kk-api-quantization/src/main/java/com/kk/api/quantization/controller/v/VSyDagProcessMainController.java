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
import com.kk.business.quantization.serviceapi.SyDagProcessMainServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.SyDagProcessMainListReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagProcessMainListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessMainAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessMainEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagProcessMainResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessMainDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessMainDeleteReqVo;
/**
 * <p>
 * DAG执行实例主表 接口
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/VSyDagProcessMain",description = "DAG执行实例主表(接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/VSyDagProcessMain")
public class VSyDagProcessMainController {

    @Resource
    public SyDagProcessMainServiceApi syDagProcessMainServiceApi;

    @Operation(summary = "获取DAG执行实例主表分页结果集", description = "获取DAG执行实例主表分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getSyDagProcessMainPageList")
    //@SaCheckPermission(value = {"v_menu_select_SyDagProcessMain"})
    public ApiResult<PageResult<SyDagProcessMainListResVo>> getSyDagProcessMainPageList(
            @RequestBody SyDagProcessMainListReqVo vo)   {
        return new ApiResult<>(syDagProcessMainServiceApi.selectSyDagProcessMainPageList(vo));
    }
    @Operation(summary = "删除DAG执行实例主表", description = "删除DAG执行实例主表")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteSyDagProcessMainById")
    //@SaCheckPermission(value={"v_btn_delete_SyDagProcessMain"})
    public ApiResult<?> deleteSyDagProcessMainById(
            @RequestBody SyDagProcessMainDeleteReqVo vo)   {
        syDagProcessMainServiceApi.deleteSyDagProcessMainById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入DAG执行实例主表", description = "插入DAG执行实例主表")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertSyDagProcessMain")
    //@SaCheckPermission(value={"v_btn_modify_SyDagProcessMain"})
    public ApiResult<?> insertSyDagProcessMain(
            @RequestBody SyDagProcessMainAddReqVo vo)   {
        syDagProcessMainServiceApi.insertSyDagProcessMain(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新DAG执行实例主表", description = "更新DAG执行实例主表")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateSyDagProcessMain")
    //@SaCheckPermission(value={"v_btn_modify_SyDagProcessMain"})
    public ApiResult<?> updateSyDagProcessMain(
            @RequestBody SyDagProcessMainEditReqVo vo)   {
        syDagProcessMainServiceApi.updateSyDagProcessMain(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询DAG执行实例主表详情", description = "查询DAG执行实例主表详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getSyDagProcessMainDetails")
    //@SaCheckPermission(value={"inc_menu_select_SyDagProcessMain"})
    public ApiResult< SyDagProcessMainResVo > getSyDagProcessMainDetails(
            @RequestBody SyDagProcessMainDetailsReqVo vo)   {
        return new  ApiResult<>(syDagProcessMainServiceApi.selectSyDagProcessMainById(vo));
    }



}
