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
import com.kk.business.quantization.serviceapi.CollectionTaskServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.CollectionTaskListReqVo;
import com.kk.business.quantization.model.vobase.res.CollectionTaskListResVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskAddReqVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskEditReqVo;
import com.kk.business.quantization.model.vobase.res.CollectionTaskResVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskDeleteReqVo;
/**
 * <p>
 * 系统设置-数据任务 内部接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnCollectionTask",description = "系统设置-数据任务(内部接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnCollectionTask")
public class InnCollectionTaskController {

    @Resource
    public CollectionTaskServiceApi collectionTaskServiceApi;

    @Operation(summary = "获取系统设置-数据任务分页结果集", description = "获取系统设置-数据任务分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getCollectionTaskPageList")
    //@SaCheckPermission(value = {"v_menu_select_CollectionTask"})
    public ApiResult<PageResult<CollectionTaskListResVo>> getCollectionTaskPageList(
            @RequestBody CollectionTaskListReqVo vo)   {
        return new ApiResult<>(collectionTaskServiceApi.selectCollectionTaskPageList(vo));
    }
    @Operation(summary = "删除系统设置-数据任务", description = "删除系统设置-数据任务")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteCollectionTaskById")
    //@SaCheckPermission(value={"v_btn_delete_CollectionTask"})
    public ApiResult<?> deleteCollectionTaskById(
            @RequestBody CollectionTaskDeleteReqVo vo)   {
        collectionTaskServiceApi.deleteCollectionTaskById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入系统设置-数据任务", description = "插入系统设置-数据任务")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertCollectionTask")
    //@SaCheckPermission(value={"v_btn_modify_CollectionTask"})
    public ApiResult<?> insertCollectionTask(
            @RequestBody CollectionTaskAddReqVo vo)   {
        collectionTaskServiceApi.insertCollectionTask(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新系统设置-数据任务", description = "更新系统设置-数据任务")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateCollectionTask")
    //@SaCheckPermission(value={"v_btn_modify_CollectionTask"})
    public ApiResult<?> updateCollectionTask(
            @RequestBody CollectionTaskEditReqVo vo)   {
        collectionTaskServiceApi.updateCollectionTask(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询系统设置-数据任务详情", description = "查询系统设置-数据任务详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getCollectionTaskDetails")
    //@SaCheckPermission(value={"inc_menu_select_CollectionTask"})
    public ApiResult< CollectionTaskResVo > getCollectionTaskDetails(
            @RequestBody CollectionTaskDetailsReqVo vo)   {
        return new  ApiResult<>(collectionTaskServiceApi.selectCollectionTaskById(vo));
    }



}
