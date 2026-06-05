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
import com.kk.business.quantization.serviceapi.IndexBasicServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.IndexBasicListReqVo;
import com.kk.business.quantization.model.vobase.res.IndexBasicListResVo;
import com.kk.business.quantization.model.vobase.req.IndexBasicAddReqVo;
import com.kk.business.quantization.model.vobase.req.IndexBasicEditReqVo;
import com.kk.business.quantization.model.vobase.res.IndexBasicResVo;
import com.kk.business.quantization.model.vobase.req.IndexBasicDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.IndexBasicDeleteReqVo;
/**
 * <p>
 * 指数基本信息 内部接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnIndexBasic",description = "指数基本信息(内部接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnIndexBasic")
public class InnIndexBasicController {

    @Resource
    public IndexBasicServiceApi indexBasicServiceApi;

    @Operation(summary = "获取指数基本信息分页结果集", description = "获取指数基本信息分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getIndexBasicPageList")
    //@SaCheckPermission(value = {"v_menu_select_IndexBasic"})
    public ApiResult<PageResult<IndexBasicListResVo>> getIndexBasicPageList(
            @RequestBody IndexBasicListReqVo vo)   {
        return new ApiResult<>(indexBasicServiceApi.selectIndexBasicPageList(vo));
    }
    @Operation(summary = "删除指数基本信息", description = "删除指数基本信息")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteIndexBasicById")
    //@SaCheckPermission(value={"v_btn_delete_IndexBasic"})
    public ApiResult<?> deleteIndexBasicById(
            @RequestBody IndexBasicDeleteReqVo vo)   {
        indexBasicServiceApi.deleteIndexBasicById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入指数基本信息", description = "插入指数基本信息")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertIndexBasic")
    //@SaCheckPermission(value={"v_btn_modify_IndexBasic"})
    public ApiResult<?> insertIndexBasic(
            @RequestBody IndexBasicAddReqVo vo)   {
        indexBasicServiceApi.insertIndexBasic(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新指数基本信息", description = "更新指数基本信息")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateIndexBasic")
    //@SaCheckPermission(value={"v_btn_modify_IndexBasic"})
    public ApiResult<?> updateIndexBasic(
            @RequestBody IndexBasicEditReqVo vo)   {
        indexBasicServiceApi.updateIndexBasic(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询指数基本信息详情", description = "查询指数基本信息详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getIndexBasicDetails")
    //@SaCheckPermission(value={"inc_menu_select_IndexBasic"})
    public ApiResult< IndexBasicResVo > getIndexBasicDetails(
            @RequestBody IndexBasicDetailsReqVo vo)   {
        return new  ApiResult<>(indexBasicServiceApi.selectIndexBasicById(vo));
    }



}
