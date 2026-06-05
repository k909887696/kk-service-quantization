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
import com.kk.business.quantization.serviceapi.StockFluctuationServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.StockFluctuationListReqVo;
import com.kk.business.quantization.model.vobase.res.StockFluctuationListResVo;
import com.kk.business.quantization.model.vobase.req.StockFluctuationAddReqVo;
import com.kk.business.quantization.model.vobase.req.StockFluctuationEditReqVo;
import com.kk.business.quantization.model.vobase.res.StockFluctuationResVo;
import com.kk.business.quantization.model.vobase.req.StockFluctuationDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.StockFluctuationDeleteReqVo;
/**
 * <p>
 * 个股异常波动信息 内部接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnStockFluctuation",description = "个股异常波动信息(内部接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnStockFluctuation")
public class InnStockFluctuationController {

    @Resource
    public StockFluctuationServiceApi stockFluctuationServiceApi;

    @Operation(summary = "获取个股异常波动信息分页结果集", description = "获取个股异常波动信息分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getStockFluctuationPageList")
    //@SaCheckPermission(value = {"v_menu_select_StockFluctuation"})
    public ApiResult<PageResult<StockFluctuationListResVo>> getStockFluctuationPageList(
            @RequestBody StockFluctuationListReqVo vo)   {
        return new ApiResult<>(stockFluctuationServiceApi.selectStockFluctuationPageList(vo));
    }
    @Operation(summary = "删除个股异常波动信息", description = "删除个股异常波动信息")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteStockFluctuationById")
    //@SaCheckPermission(value={"v_btn_delete_StockFluctuation"})
    public ApiResult<?> deleteStockFluctuationById(
            @RequestBody StockFluctuationDeleteReqVo vo)   {
        stockFluctuationServiceApi.deleteStockFluctuationById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入个股异常波动信息", description = "插入个股异常波动信息")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertStockFluctuation")
    //@SaCheckPermission(value={"v_btn_modify_StockFluctuation"})
    public ApiResult<?> insertStockFluctuation(
            @RequestBody StockFluctuationAddReqVo vo)   {
        stockFluctuationServiceApi.insertStockFluctuation(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新个股异常波动信息", description = "更新个股异常波动信息")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateStockFluctuation")
    //@SaCheckPermission(value={"v_btn_modify_StockFluctuation"})
    public ApiResult<?> updateStockFluctuation(
            @RequestBody StockFluctuationEditReqVo vo)   {
        stockFluctuationServiceApi.updateStockFluctuation(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询个股异常波动信息详情", description = "查询个股异常波动信息详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getStockFluctuationDetails")
    //@SaCheckPermission(value={"inc_menu_select_StockFluctuation"})
    public ApiResult< StockFluctuationResVo > getStockFluctuationDetails(
            @RequestBody StockFluctuationDetailsReqVo vo)   {
        return new  ApiResult<>(stockFluctuationServiceApi.selectStockFluctuationById(vo));
    }



}
