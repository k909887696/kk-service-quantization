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
import com.kk.business.quantization.serviceapi.CollectionPolicyServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.CollectionPolicyListReqVo;
import com.kk.business.quantization.model.vobase.res.CollectionPolicyListResVo;
import com.kk.business.quantization.model.vobase.req.CollectionPolicyAddReqVo;
import com.kk.business.quantization.model.vobase.req.CollectionPolicyEditReqVo;
import com.kk.business.quantization.model.vobase.res.CollectionPolicyResVo;
import com.kk.business.quantization.model.vobase.req.CollectionPolicyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.CollectionPolicyDeleteReqVo;
/**
 * <p>
 * 系统设置-数据策略 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/VCollectionPolicy",description = "系统设置-数据策略(接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/VCollectionPolicy")
public class VCollectionPolicyController {

    @Resource
    public CollectionPolicyServiceApi collectionPolicyServiceApi;

    @Operation(summary = "获取系统设置-数据策略分页结果集", description = "获取系统设置-数据策略分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getCollectionPolicyPageList")
    //@SaCheckPermission(value = {"v_menu_select_CollectionPolicy"})
    public ApiResult<PageResult<CollectionPolicyListResVo>> getCollectionPolicyPageList(
            @RequestBody CollectionPolicyListReqVo vo)   {
        return new ApiResult<>(collectionPolicyServiceApi.selectCollectionPolicyPageList(vo));
    }
    @Operation(summary = "删除系统设置-数据策略", description = "删除系统设置-数据策略")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteCollectionPolicyById")
    //@SaCheckPermission(value={"v_btn_delete_CollectionPolicy"})
    public ApiResult<?> deleteCollectionPolicyById(
            @RequestBody CollectionPolicyDeleteReqVo vo)   {
        collectionPolicyServiceApi.deleteCollectionPolicyById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入系统设置-数据策略", description = "插入系统设置-数据策略")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertCollectionPolicy")
    //@SaCheckPermission(value={"v_btn_modify_CollectionPolicy"})
    public ApiResult<?> insertCollectionPolicy(
            @RequestBody CollectionPolicyAddReqVo vo)   {
        collectionPolicyServiceApi.insertCollectionPolicy(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新系统设置-数据策略", description = "更新系统设置-数据策略")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateCollectionPolicy")
    //@SaCheckPermission(value={"v_btn_modify_CollectionPolicy"})
    public ApiResult<?> updateCollectionPolicy(
            @RequestBody CollectionPolicyEditReqVo vo)   {
        collectionPolicyServiceApi.updateCollectionPolicy(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询系统设置-数据策略详情", description = "查询系统设置-数据策略详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getCollectionPolicyDetails")
    //@SaCheckPermission(value={"inc_menu_select_CollectionPolicy"})
    public ApiResult< CollectionPolicyResVo > getCollectionPolicyDetails(
            @RequestBody CollectionPolicyDetailsReqVo vo)   {
        return new  ApiResult<>(collectionPolicyServiceApi.selectCollectionPolicyById(vo));
    }



}
