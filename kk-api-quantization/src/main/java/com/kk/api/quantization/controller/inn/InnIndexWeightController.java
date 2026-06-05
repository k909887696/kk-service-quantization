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
import com.kk.business.quantization.serviceapi.IndexWeightServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.IndexWeightListReqVo;
import com.kk.business.quantization.model.vobase.res.IndexWeightListResVo;
import com.kk.business.quantization.model.vobase.req.IndexWeightAddReqVo;
import com.kk.business.quantization.model.vobase.req.IndexWeightEditReqVo;
import com.kk.business.quantization.model.vobase.res.IndexWeightResVo;
import com.kk.business.quantization.model.vobase.req.IndexWeightDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.IndexWeightDeleteReqVo;
/**
 * <p>
 * 指数成分权重 内部接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnIndexWeight",description = "指数成分权重(内部接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnIndexWeight")
public class InnIndexWeightController {

    @Resource
    public IndexWeightServiceApi indexWeightServiceApi;

    @Operation(summary = "获取指数成分权重分页结果集", description = "获取指数成分权重分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getIndexWeightPageList")
    //@SaCheckPermission(value = {"v_menu_select_IndexWeight"})
    public ApiResult<PageResult<IndexWeightListResVo>> getIndexWeightPageList(
            @RequestBody IndexWeightListReqVo vo)   {
        return new ApiResult<>(indexWeightServiceApi.selectIndexWeightPageList(vo));
    }
    @Operation(summary = "删除指数成分权重", description = "删除指数成分权重")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteIndexWeightById")
    //@SaCheckPermission(value={"v_btn_delete_IndexWeight"})
    public ApiResult<?> deleteIndexWeightById(
            @RequestBody IndexWeightDeleteReqVo vo)   {
        indexWeightServiceApi.deleteIndexWeightById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入指数成分权重", description = "插入指数成分权重")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertIndexWeight")
    //@SaCheckPermission(value={"v_btn_modify_IndexWeight"})
    public ApiResult<?> insertIndexWeight(
            @RequestBody IndexWeightAddReqVo vo)   {
        indexWeightServiceApi.insertIndexWeight(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新指数成分权重", description = "更新指数成分权重")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateIndexWeight")
    //@SaCheckPermission(value={"v_btn_modify_IndexWeight"})
    public ApiResult<?> updateIndexWeight(
            @RequestBody IndexWeightEditReqVo vo)   {
        indexWeightServiceApi.updateIndexWeight(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询指数成分权重详情", description = "查询指数成分权重详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getIndexWeightDetails")
    //@SaCheckPermission(value={"inc_menu_select_IndexWeight"})
    public ApiResult< IndexWeightResVo > getIndexWeightDetails(
            @RequestBody IndexWeightDetailsReqVo vo)   {
        return new  ApiResult<>(indexWeightServiceApi.selectIndexWeightById(vo));
    }



}
