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
import com.kk.business.quantization.serviceapi.MonthlyServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.MonthlyListReqVo;
import com.kk.business.quantization.model.vobase.res.MonthlyListResVo;
import com.kk.business.quantization.model.vobase.req.MonthlyAddReqVo;
import com.kk.business.quantization.model.vobase.req.MonthlyEditReqVo;
import com.kk.business.quantization.model.vobase.res.MonthlyResVo;
import com.kk.business.quantization.model.vobase.req.MonthlyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.MonthlyDeleteReqVo;
/**
 * <p>
 * 个股月线行情 内部接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnMonthly",description = "个股月线行情(内部接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnMonthly")
public class InnMonthlyController {

    @Resource
    public MonthlyServiceApi monthlyServiceApi;

    @Operation(summary = "获取个股月线行情分页结果集", description = "获取个股月线行情分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getMonthlyPageList")
    //@SaCheckPermission(value = {"v_menu_select_Monthly"})
    public ApiResult<PageResult<MonthlyListResVo>> getMonthlyPageList(
            @RequestBody MonthlyListReqVo vo)   {
        return new ApiResult<>(monthlyServiceApi.selectMonthlyPageList(vo));
    }
    @Operation(summary = "删除个股月线行情", description = "删除个股月线行情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteMonthlyById")
    //@SaCheckPermission(value={"v_btn_delete_Monthly"})
    public ApiResult<?> deleteMonthlyById(
            @RequestBody MonthlyDeleteReqVo vo)   {
        monthlyServiceApi.deleteMonthlyById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入个股月线行情", description = "插入个股月线行情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertMonthly")
    //@SaCheckPermission(value={"v_btn_modify_Monthly"})
    public ApiResult<?> insertMonthly(
            @RequestBody MonthlyAddReqVo vo)   {
        monthlyServiceApi.insertMonthly(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新个股月线行情", description = "更新个股月线行情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateMonthly")
    //@SaCheckPermission(value={"v_btn_modify_Monthly"})
    public ApiResult<?> updateMonthly(
            @RequestBody MonthlyEditReqVo vo)   {
        monthlyServiceApi.updateMonthly(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询个股月线行情详情", description = "查询个股月线行情详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getMonthlyDetails")
    //@SaCheckPermission(value={"inc_menu_select_Monthly"})
    public ApiResult< MonthlyResVo > getMonthlyDetails(
            @RequestBody MonthlyDetailsReqVo vo)   {
        return new  ApiResult<>(monthlyServiceApi.selectMonthlyById(vo));
    }



}
