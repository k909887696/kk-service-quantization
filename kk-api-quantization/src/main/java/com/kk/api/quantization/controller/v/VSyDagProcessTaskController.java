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
import com.kk.business.quantization.serviceapi.SyDagProcessTaskServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskListReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagProcessTaskListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagProcessTaskResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskDeleteReqVo;
/**
 * <p>
 * DAG执行实例任务 接口
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/VSyDagProcessTask",description = "DAG执行实例任务(接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/VSyDagProcessTask")
public class VSyDagProcessTaskController {

    @Resource
    public SyDagProcessTaskServiceApi syDagProcessTaskServiceApi;

    @Operation(summary = "获取DAG执行实例任务分页结果集", description = "获取DAG执行实例任务分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getSyDagProcessTaskPageList")
    //@SaCheckPermission(value = {"v_menu_select_SyDagProcessTask"})
    public ApiResult<PageResult<SyDagProcessTaskListResVo>> getSyDagProcessTaskPageList(
            @RequestBody SyDagProcessTaskListReqVo vo)   {
        return new ApiResult<>(syDagProcessTaskServiceApi.selectSyDagProcessTaskPageList(vo));
    }
    @Operation(summary = "删除DAG执行实例任务", description = "删除DAG执行实例任务")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteSyDagProcessTaskById")
    //@SaCheckPermission(value={"v_btn_delete_SyDagProcessTask"})
    public ApiResult<?> deleteSyDagProcessTaskById(
            @RequestBody SyDagProcessTaskDeleteReqVo vo)   {
        syDagProcessTaskServiceApi.deleteSyDagProcessTaskById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入DAG执行实例任务", description = "插入DAG执行实例任务")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertSyDagProcessTask")
    //@SaCheckPermission(value={"v_btn_modify_SyDagProcessTask"})
    public ApiResult<?> insertSyDagProcessTask(
            @RequestBody SyDagProcessTaskAddReqVo vo)   {
        syDagProcessTaskServiceApi.insertSyDagProcessTask(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新DAG执行实例任务", description = "更新DAG执行实例任务")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateSyDagProcessTask")
    //@SaCheckPermission(value={"v_btn_modify_SyDagProcessTask"})
    public ApiResult<?> updateSyDagProcessTask(
            @RequestBody SyDagProcessTaskEditReqVo vo)   {
        syDagProcessTaskServiceApi.updateSyDagProcessTask(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询DAG执行实例任务详情", description = "查询DAG执行实例任务详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getSyDagProcessTaskDetails")
    //@SaCheckPermission(value={"inc_menu_select_SyDagProcessTask"})
    public ApiResult< SyDagProcessTaskResVo > getSyDagProcessTaskDetails(
            @RequestBody SyDagProcessTaskDetailsReqVo vo)   {
        return new  ApiResult<>(syDagProcessTaskServiceApi.selectSyDagProcessTaskById(vo));
    }



}
