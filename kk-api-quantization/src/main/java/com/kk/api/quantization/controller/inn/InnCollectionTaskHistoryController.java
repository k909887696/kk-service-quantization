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
import com.kk.business.quantization.serviceapi.CollectionTaskHistoryServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.CollectionTaskHistoryListReqVo;
import com.kk.business.quantization.model.vobase.res.CollectionTaskHistoryListResVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskHistoryAddReqVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskHistoryEditReqVo;
import com.kk.business.quantization.model.vobase.res.CollectionTaskHistoryResVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskHistoryDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskHistoryDeleteReqVo;
/**
 * <p>
 * 系统设置-数据任务-历史 内部接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnCollectionTaskHistory",description = "系统设置-数据任务-历史(内部接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnCollectionTaskHistory")
public class InnCollectionTaskHistoryController {

    @Resource
    public CollectionTaskHistoryServiceApi collectionTaskHistoryServiceApi;

    @Operation(summary = "获取系统设置-数据任务-历史分页结果集", description = "获取系统设置-数据任务-历史分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getCollectionTaskHistoryPageList")
    //@SaCheckPermission(value = {"v_menu_select_CollectionTaskHistory"})
    public ApiResult<PageResult<CollectionTaskHistoryListResVo>> getCollectionTaskHistoryPageList(
            @RequestBody CollectionTaskHistoryListReqVo vo)   {
        return new ApiResult<>(collectionTaskHistoryServiceApi.selectCollectionTaskHistoryPageList(vo));
    }
    @Operation(summary = "删除系统设置-数据任务-历史", description = "删除系统设置-数据任务-历史")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteCollectionTaskHistoryById")
    //@SaCheckPermission(value={"v_btn_delete_CollectionTaskHistory"})
    public ApiResult<?> deleteCollectionTaskHistoryById(
            @RequestBody CollectionTaskHistoryDeleteReqVo vo)   {
        collectionTaskHistoryServiceApi.deleteCollectionTaskHistoryById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入系统设置-数据任务-历史", description = "插入系统设置-数据任务-历史")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertCollectionTaskHistory")
    //@SaCheckPermission(value={"v_btn_modify_CollectionTaskHistory"})
    public ApiResult<?> insertCollectionTaskHistory(
            @RequestBody CollectionTaskHistoryAddReqVo vo)   {
        collectionTaskHistoryServiceApi.insertCollectionTaskHistory(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新系统设置-数据任务-历史", description = "更新系统设置-数据任务-历史")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateCollectionTaskHistory")
    //@SaCheckPermission(value={"v_btn_modify_CollectionTaskHistory"})
    public ApiResult<?> updateCollectionTaskHistory(
            @RequestBody CollectionTaskHistoryEditReqVo vo)   {
        collectionTaskHistoryServiceApi.updateCollectionTaskHistory(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询系统设置-数据任务-历史详情", description = "查询系统设置-数据任务-历史详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getCollectionTaskHistoryDetails")
    //@SaCheckPermission(value={"inc_menu_select_CollectionTaskHistory"})
    public ApiResult< CollectionTaskHistoryResVo > getCollectionTaskHistoryDetails(
            @RequestBody CollectionTaskHistoryDetailsReqVo vo)   {
        return new  ApiResult<>(collectionTaskHistoryServiceApi.selectCollectionTaskHistoryById(vo));
    }



}
