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
import com.kk.business.quantization.serviceapi.TradeCalServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.TradeCalListReqVo;
import com.kk.business.quantization.model.vobase.res.TradeCalListResVo;
import com.kk.business.quantization.model.vobase.req.TradeCalAddReqVo;
import com.kk.business.quantization.model.vobase.req.TradeCalEditReqVo;
import com.kk.business.quantization.model.vobase.res.TradeCalResVo;
import com.kk.business.quantization.model.vobase.req.TradeCalDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.TradeCalDeleteReqVo;
/**
 * <p>
 * 交易日历 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/VTradeCal",description = "交易日历(接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/VTradeCal")
public class VTradeCalController {

    @Resource
    public TradeCalServiceApi tradeCalServiceApi;

    @Operation(summary = "获取交易日历分页结果集", description = "获取交易日历分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getTradeCalPageList")
    //@SaCheckPermission(value = {"v_menu_select_TradeCal"})
    public ApiResult<PageResult<TradeCalListResVo>> getTradeCalPageList(
            @RequestBody TradeCalListReqVo vo)   {
        return new ApiResult<>(tradeCalServiceApi.selectTradeCalPageList(vo));
    }
    @Operation(summary = "删除交易日历", description = "删除交易日历")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteTradeCalById")
    //@SaCheckPermission(value={"v_btn_delete_TradeCal"})
    public ApiResult<?> deleteTradeCalById(
            @RequestBody TradeCalDeleteReqVo vo)   {
        tradeCalServiceApi.deleteTradeCalById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入交易日历", description = "插入交易日历")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertTradeCal")
    //@SaCheckPermission(value={"v_btn_modify_TradeCal"})
    public ApiResult<?> insertTradeCal(
            @RequestBody TradeCalAddReqVo vo)   {
        tradeCalServiceApi.insertTradeCal(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新交易日历", description = "更新交易日历")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateTradeCal")
    //@SaCheckPermission(value={"v_btn_modify_TradeCal"})
    public ApiResult<?> updateTradeCal(
            @RequestBody TradeCalEditReqVo vo)   {
        tradeCalServiceApi.updateTradeCal(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询交易日历详情", description = "查询交易日历详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getTradeCalDetails")
    //@SaCheckPermission(value={"inc_menu_select_TradeCal"})
    public ApiResult< TradeCalResVo > getTradeCalDetails(
            @RequestBody TradeCalDetailsReqVo vo)   {
        return new  ApiResult<>(tradeCalServiceApi.selectTradeCalById(vo));
    }



}
