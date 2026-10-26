package com.kk.business.quantization.controller.inn;


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
import com.kk.business.quantization.serviceapi.SyDagMainServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.SyDagMainListReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagMainListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagMainAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagMainEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagMainResVo;
import com.kk.business.quantization.model.vobase.req.SyDagMainDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagMainDeleteReqVo;
/**
 * <p>
 * DAG主表 内部接口
 * </p>
 *
 * @author kk
 * @since 2026-10-22
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnSyDagMain",description = "DAG主表(内部接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnSyDagMain")
public class InnSyDagMainController {

    @Resource
    public SyDagMainServiceApi syDagMainServiceApi;

    @Operation(summary = "获取DAG主表分页结果集", description = "获取DAG主表分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getSyDagMainPageList")
    //@SaCheckPermission(value = {"v_menu_select_SyDagMain"})
    public ApiResult<PageResult<SyDagMainListResVo>> getSyDagMainPageList(
            @RequestBody SyDagMainListReqVo vo)   {
        return new ApiResult<>(syDagMainServiceApi.selectSyDagMainPageList(vo));
    }
    @Operation(summary = "删除DAG主表", description = "删除DAG主表")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteSyDagMainById")
    //@SaCheckPermission(value={"v_btn_delete_SyDagMain"})
    public ApiResult<?> deleteSyDagMainById(
            @RequestBody SyDagMainDeleteReqVo vo)   {
        syDagMainServiceApi.deleteSyDagMainById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入DAG主表", description = "插入DAG主表")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertSyDagMain")
    //@SaCheckPermission(value={"v_btn_modify_SyDagMain"})
    public ApiResult<?> insertSyDagMain(
            @RequestBody SyDagMainAddReqVo vo)   {
        syDagMainServiceApi.insertSyDagMain(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新DAG主表", description = "更新DAG主表")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateSyDagMain")
    //@SaCheckPermission(value={"v_btn_modify_SyDagMain"})
    public ApiResult<?> updateSyDagMain(
            @RequestBody SyDagMainEditReqVo vo)   {
        syDagMainServiceApi.updateSyDagMain(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询DAG主表详情", description = "查询DAG主表详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getSyDagMainDetails")
    //@SaCheckPermission(value={"inc_menu_select_SyDagMain"})
    public ApiResult< SyDagMainResVo > getSyDagMainDetails(
            @RequestBody SyDagMainDetailsReqVo vo)   {
        return new  ApiResult<>(syDagMainServiceApi.selectSyDagMainById(vo));
    }



}
