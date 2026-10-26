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
import com.kk.business.quantization.serviceapi.SyDagNodeServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.SyDagNodeListReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagNodeListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagNodeAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagNodeEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagNodeResVo;
import com.kk.business.quantization.model.vobase.req.SyDagNodeDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagNodeDeleteReqVo;
/**
 * <p>
 * DAG执行节点 内部接口
 * </p>
 *
 * @author kk
 * @since 2026-10-22
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnSyDagNode",description = "DAG执行节点(内部接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnSyDagNode")
public class InnSyDagNodeController {

    @Resource
    public SyDagNodeServiceApi syDagNodeServiceApi;

    @Operation(summary = "获取DAG执行节点分页结果集", description = "获取DAG执行节点分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getSyDagNodePageList")
    //@SaCheckPermission(value = {"v_menu_select_SyDagNode"})
    public ApiResult<PageResult<SyDagNodeListResVo>> getSyDagNodePageList(
            @RequestBody SyDagNodeListReqVo vo)   {
        return new ApiResult<>(syDagNodeServiceApi.selectSyDagNodePageList(vo));
    }
    @Operation(summary = "删除DAG执行节点", description = "删除DAG执行节点")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteSyDagNodeById")
    //@SaCheckPermission(value={"v_btn_delete_SyDagNode"})
    public ApiResult<?> deleteSyDagNodeById(
            @RequestBody SyDagNodeDeleteReqVo vo)   {
        syDagNodeServiceApi.deleteSyDagNodeById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入DAG执行节点", description = "插入DAG执行节点")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertSyDagNode")
    //@SaCheckPermission(value={"v_btn_modify_SyDagNode"})
    public ApiResult<?> insertSyDagNode(
            @RequestBody SyDagNodeAddReqVo vo)   {
        syDagNodeServiceApi.insertSyDagNode(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新DAG执行节点", description = "更新DAG执行节点")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateSyDagNode")
    //@SaCheckPermission(value={"v_btn_modify_SyDagNode"})
    public ApiResult<?> updateSyDagNode(
            @RequestBody SyDagNodeEditReqVo vo)   {
        syDagNodeServiceApi.updateSyDagNode(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询DAG执行节点详情", description = "查询DAG执行节点详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getSyDagNodeDetails")
    //@SaCheckPermission(value={"inc_menu_select_SyDagNode"})
    public ApiResult< SyDagNodeResVo > getSyDagNodeDetails(
            @RequestBody SyDagNodeDetailsReqVo vo)   {
        return new  ApiResult<>(syDagNodeServiceApi.selectSyDagNodeById(vo));
    }



}
