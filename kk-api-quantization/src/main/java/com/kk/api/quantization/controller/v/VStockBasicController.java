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
import com.kk.business.quantization.serviceapi.StockBasicServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.StockBasicListReqVo;
import com.kk.business.quantization.model.vobase.res.StockBasicListResVo;
import com.kk.business.quantization.model.vobase.req.StockBasicAddReqVo;
import com.kk.business.quantization.model.vobase.req.StockBasicEditReqVo;
import com.kk.business.quantization.model.vobase.res.StockBasicResVo;
import com.kk.business.quantization.model.vobase.req.StockBasicDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.StockBasicDeleteReqVo;
/**
 * <p>
 * 个股基本信息 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/VStockBasic",description = "个股基本信息(接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/VStockBasic")
public class VStockBasicController {

    @Resource
    public StockBasicServiceApi stockBasicServiceApi;

    @Operation(summary = "获取个股基本信息分页结果集", description = "获取个股基本信息分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getStockBasicPageList")
    //@SaCheckPermission(value = {"v_menu_select_StockBasic"})
    public ApiResult<PageResult<StockBasicListResVo>> getStockBasicPageList(
            @RequestBody StockBasicListReqVo vo)   {
        return new ApiResult<>(stockBasicServiceApi.selectStockBasicPageList(vo));
    }
    @Operation(summary = "删除个股基本信息", description = "删除个股基本信息")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteStockBasicById")
    //@SaCheckPermission(value={"v_btn_delete_StockBasic"})
    public ApiResult<?> deleteStockBasicById(
            @RequestBody StockBasicDeleteReqVo vo)   {
        stockBasicServiceApi.deleteStockBasicById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入个股基本信息", description = "插入个股基本信息")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertStockBasic")
    //@SaCheckPermission(value={"v_btn_modify_StockBasic"})
    public ApiResult<?> insertStockBasic(
            @RequestBody StockBasicAddReqVo vo)   {
        stockBasicServiceApi.insertStockBasic(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新个股基本信息", description = "更新个股基本信息")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateStockBasic")
    //@SaCheckPermission(value={"v_btn_modify_StockBasic"})
    public ApiResult<?> updateStockBasic(
            @RequestBody StockBasicEditReqVo vo)   {
        stockBasicServiceApi.updateStockBasic(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询个股基本信息详情", description = "查询个股基本信息详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getStockBasicDetails")
    //@SaCheckPermission(value={"inc_menu_select_StockBasic"})
    public ApiResult< StockBasicResVo > getStockBasicDetails(
            @RequestBody StockBasicDetailsReqVo vo)   {
        return new  ApiResult<>(stockBasicServiceApi.selectStockBasicById(vo));
    }



}
