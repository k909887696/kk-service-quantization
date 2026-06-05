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
import com.kk.business.quantization.serviceapi.StockDayKdjServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.StockDayKdjListReqVo;
import com.kk.business.quantization.model.vobase.res.StockDayKdjListResVo;
import com.kk.business.quantization.model.vobase.req.StockDayKdjAddReqVo;
import com.kk.business.quantization.model.vobase.req.StockDayKdjEditReqVo;
import com.kk.business.quantization.model.vobase.res.StockDayKdjResVo;
import com.kk.business.quantization.model.vobase.req.StockDayKdjDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.StockDayKdjDeleteReqVo;
/**
 * <p>
 * 个股kdj数据 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/VStockDayKdj",description = "个股kdj数据(接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/VStockDayKdj")
public class VStockDayKdjController {

    @Resource
    public StockDayKdjServiceApi stockDayKdjServiceApi;

    @Operation(summary = "获取个股kdj数据分页结果集", description = "获取个股kdj数据分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getStockDayKdjPageList")
    //@SaCheckPermission(value = {"v_menu_select_StockDayKdj"})
    public ApiResult<PageResult<StockDayKdjListResVo>> getStockDayKdjPageList(
            @RequestBody StockDayKdjListReqVo vo)   {
        return new ApiResult<>(stockDayKdjServiceApi.selectStockDayKdjPageList(vo));
    }
    @Operation(summary = "删除个股kdj数据", description = "删除个股kdj数据")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteStockDayKdjById")
    //@SaCheckPermission(value={"v_btn_delete_StockDayKdj"})
    public ApiResult<?> deleteStockDayKdjById(
            @RequestBody StockDayKdjDeleteReqVo vo)   {
        stockDayKdjServiceApi.deleteStockDayKdjById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入个股kdj数据", description = "插入个股kdj数据")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertStockDayKdj")
    //@SaCheckPermission(value={"v_btn_modify_StockDayKdj"})
    public ApiResult<?> insertStockDayKdj(
            @RequestBody StockDayKdjAddReqVo vo)   {
        stockDayKdjServiceApi.insertStockDayKdj(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新个股kdj数据", description = "更新个股kdj数据")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateStockDayKdj")
    //@SaCheckPermission(value={"v_btn_modify_StockDayKdj"})
    public ApiResult<?> updateStockDayKdj(
            @RequestBody StockDayKdjEditReqVo vo)   {
        stockDayKdjServiceApi.updateStockDayKdj(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询个股kdj数据详情", description = "查询个股kdj数据详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getStockDayKdjDetails")
    //@SaCheckPermission(value={"inc_menu_select_StockDayKdj"})
    public ApiResult< StockDayKdjResVo > getStockDayKdjDetails(
            @RequestBody StockDayKdjDetailsReqVo vo)   {
        return new  ApiResult<>(stockDayKdjServiceApi.selectStockDayKdjById(vo));
    }



}
