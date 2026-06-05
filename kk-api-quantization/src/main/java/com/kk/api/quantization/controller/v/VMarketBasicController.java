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
import com.kk.business.quantization.serviceapi.MarketBasicServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.MarketBasicListReqVo;
import com.kk.business.quantization.model.vobase.res.MarketBasicListResVo;
import com.kk.business.quantization.model.vobase.req.MarketBasicAddReqVo;
import com.kk.business.quantization.model.vobase.req.MarketBasicEditReqVo;
import com.kk.business.quantization.model.vobase.res.MarketBasicResVo;
import com.kk.business.quantization.model.vobase.req.MarketBasicDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.MarketBasicDeleteReqVo;
/**
 * <p>
 * 市场基本信息 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/VMarketBasic",description = "市场基本信息(接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/VMarketBasic")
public class VMarketBasicController {

    @Resource
    public MarketBasicServiceApi marketBasicServiceApi;

    @Operation(summary = "获取市场基本信息分页结果集", description = "获取市场基本信息分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getMarketBasicPageList")
    //@SaCheckPermission(value = {"v_menu_select_MarketBasic"})
    public ApiResult<PageResult<MarketBasicListResVo>> getMarketBasicPageList(
            @RequestBody MarketBasicListReqVo vo)   {
        return new ApiResult<>(marketBasicServiceApi.selectMarketBasicPageList(vo));
    }
    @Operation(summary = "删除市场基本信息", description = "删除市场基本信息")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteMarketBasicById")
    //@SaCheckPermission(value={"v_btn_delete_MarketBasic"})
    public ApiResult<?> deleteMarketBasicById(
            @RequestBody MarketBasicDeleteReqVo vo)   {
        marketBasicServiceApi.deleteMarketBasicById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入市场基本信息", description = "插入市场基本信息")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertMarketBasic")
    //@SaCheckPermission(value={"v_btn_modify_MarketBasic"})
    public ApiResult<?> insertMarketBasic(
            @RequestBody MarketBasicAddReqVo vo)   {
        marketBasicServiceApi.insertMarketBasic(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新市场基本信息", description = "更新市场基本信息")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateMarketBasic")
    //@SaCheckPermission(value={"v_btn_modify_MarketBasic"})
    public ApiResult<?> updateMarketBasic(
            @RequestBody MarketBasicEditReqVo vo)   {
        marketBasicServiceApi.updateMarketBasic(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询市场基本信息详情", description = "查询市场基本信息详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getMarketBasicDetails")
    //@SaCheckPermission(value={"inc_menu_select_MarketBasic"})
    public ApiResult< MarketBasicResVo > getMarketBasicDetails(
            @RequestBody MarketBasicDetailsReqVo vo)   {
        return new  ApiResult<>(marketBasicServiceApi.selectMarketBasicById(vo));
    }



}
