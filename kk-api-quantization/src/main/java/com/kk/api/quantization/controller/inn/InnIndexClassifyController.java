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
import com.kk.business.quantization.serviceapi.IndexClassifyServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.IndexClassifyListReqVo;
import com.kk.business.quantization.model.vobase.res.IndexClassifyListResVo;
import com.kk.business.quantization.model.vobase.req.IndexClassifyAddReqVo;
import com.kk.business.quantization.model.vobase.req.IndexClassifyEditReqVo;
import com.kk.business.quantization.model.vobase.res.IndexClassifyResVo;
import com.kk.business.quantization.model.vobase.req.IndexClassifyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.IndexClassifyDeleteReqVo;
/**
 * <p>
 * 申万行业分类 内部接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnIndexClassify",description = "申万行业分类(内部接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnIndexClassify")
public class InnIndexClassifyController {

    @Resource
    public IndexClassifyServiceApi indexClassifyServiceApi;

    @Operation(summary = "获取申万行业分类分页结果集", description = "获取申万行业分类分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getIndexClassifyPageList")
    //@SaCheckPermission(value = {"v_menu_select_IndexClassify"})
    public ApiResult<PageResult<IndexClassifyListResVo>> getIndexClassifyPageList(
            @RequestBody IndexClassifyListReqVo vo)   {
        return new ApiResult<>(indexClassifyServiceApi.selectIndexClassifyPageList(vo));
    }
    @Operation(summary = "删除申万行业分类", description = "删除申万行业分类")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteIndexClassifyById")
    //@SaCheckPermission(value={"v_btn_delete_IndexClassify"})
    public ApiResult<?> deleteIndexClassifyById(
            @RequestBody IndexClassifyDeleteReqVo vo)   {
        indexClassifyServiceApi.deleteIndexClassifyById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入申万行业分类", description = "插入申万行业分类")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertIndexClassify")
    //@SaCheckPermission(value={"v_btn_modify_IndexClassify"})
    public ApiResult<?> insertIndexClassify(
            @RequestBody IndexClassifyAddReqVo vo)   {
        indexClassifyServiceApi.insertIndexClassify(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新申万行业分类", description = "更新申万行业分类")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateIndexClassify")
    //@SaCheckPermission(value={"v_btn_modify_IndexClassify"})
    public ApiResult<?> updateIndexClassify(
            @RequestBody IndexClassifyEditReqVo vo)   {
        indexClassifyServiceApi.updateIndexClassify(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询申万行业分类详情", description = "查询申万行业分类详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getIndexClassifyDetails")
    //@SaCheckPermission(value={"inc_menu_select_IndexClassify"})
    public ApiResult< IndexClassifyResVo > getIndexClassifyDetails(
            @RequestBody IndexClassifyDetailsReqVo vo)   {
        return new  ApiResult<>(indexClassifyServiceApi.selectIndexClassifyById(vo));
    }



}
