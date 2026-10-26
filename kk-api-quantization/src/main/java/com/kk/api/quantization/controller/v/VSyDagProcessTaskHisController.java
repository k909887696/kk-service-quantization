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
import com.kk.business.quantization.serviceapi.SyDagProcessTaskHisServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskHisListReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagProcessTaskHisListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskHisAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskHisEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagProcessTaskHisResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskHisDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskHisDeleteReqVo;
/**
 * <p>
 * DAG执行实例历史任务 接口
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/VSyDagProcessTaskHis",description = "DAG执行实例历史任务(接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/VSyDagProcessTaskHis")
public class VSyDagProcessTaskHisController {

    @Resource
    public SyDagProcessTaskHisServiceApi syDagProcessTaskHisServiceApi;

    @Operation(summary = "获取DAG执行实例历史任务分页结果集", description = "获取DAG执行实例历史任务分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getSyDagProcessTaskHisPageList")
    //@SaCheckPermission(value = {"v_menu_select_SyDagProcessTaskHis"})
    public ApiResult<PageResult<SyDagProcessTaskHisListResVo>> getSyDagProcessTaskHisPageList(
            @RequestBody SyDagProcessTaskHisListReqVo vo)   {
        return new ApiResult<>(syDagProcessTaskHisServiceApi.selectSyDagProcessTaskHisPageList(vo));
    }
    @Operation(summary = "删除DAG执行实例历史任务", description = "删除DAG执行实例历史任务")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteSyDagProcessTaskHisById")
    //@SaCheckPermission(value={"v_btn_delete_SyDagProcessTaskHis"})
    public ApiResult<?> deleteSyDagProcessTaskHisById(
            @RequestBody SyDagProcessTaskHisDeleteReqVo vo)   {
        syDagProcessTaskHisServiceApi.deleteSyDagProcessTaskHisById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入DAG执行实例历史任务", description = "插入DAG执行实例历史任务")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertSyDagProcessTaskHis")
    //@SaCheckPermission(value={"v_btn_modify_SyDagProcessTaskHis"})
    public ApiResult<?> insertSyDagProcessTaskHis(
            @RequestBody SyDagProcessTaskHisAddReqVo vo)   {
        syDagProcessTaskHisServiceApi.insertSyDagProcessTaskHis(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新DAG执行实例历史任务", description = "更新DAG执行实例历史任务")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateSyDagProcessTaskHis")
    //@SaCheckPermission(value={"v_btn_modify_SyDagProcessTaskHis"})
    public ApiResult<?> updateSyDagProcessTaskHis(
            @RequestBody SyDagProcessTaskHisEditReqVo vo)   {
        syDagProcessTaskHisServiceApi.updateSyDagProcessTaskHis(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询DAG执行实例历史任务详情", description = "查询DAG执行实例历史任务详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getSyDagProcessTaskHisDetails")
    //@SaCheckPermission(value={"inc_menu_select_SyDagProcessTaskHis"})
    public ApiResult< SyDagProcessTaskHisResVo > getSyDagProcessTaskHisDetails(
            @RequestBody SyDagProcessTaskHisDetailsReqVo vo)   {
        return new  ApiResult<>(syDagProcessTaskHisServiceApi.selectSyDagProcessTaskHisById(vo));
    }



}
