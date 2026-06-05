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
import com.kk.business.quantization.serviceapi.DailyBasicServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.DailyBasicListReqVo;
import com.kk.business.quantization.model.vobase.res.DailyBasicListResVo;
import com.kk.business.quantization.model.vobase.req.DailyBasicAddReqVo;
import com.kk.business.quantization.model.vobase.req.DailyBasicEditReqVo;
import com.kk.business.quantization.model.vobase.res.DailyBasicResVo;
import com.kk.business.quantization.model.vobase.req.DailyBasicDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.DailyBasicDeleteReqVo;
/**
 * <p>
 * 个股每日指标 内部接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnDailyBasic",description = "个股每日指标(内部接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnDailyBasic")
public class InnDailyBasicController {

    @Resource
    public DailyBasicServiceApi dailyBasicServiceApi;

    @Operation(summary = "获取个股每日指标分页结果集", description = "获取个股每日指标分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getDailyBasicPageList")
    //@SaCheckPermission(value = {"v_menu_select_DailyBasic"})
    public ApiResult<PageResult<DailyBasicListResVo>> getDailyBasicPageList(
            @RequestBody DailyBasicListReqVo vo)   {
        return new ApiResult<>(dailyBasicServiceApi.selectDailyBasicPageList(vo));
    }
    @Operation(summary = "删除个股每日指标", description = "删除个股每日指标")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteDailyBasicById")
    //@SaCheckPermission(value={"v_btn_delete_DailyBasic"})
    public ApiResult<?> deleteDailyBasicById(
            @RequestBody DailyBasicDeleteReqVo vo)   {
        dailyBasicServiceApi.deleteDailyBasicById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入个股每日指标", description = "插入个股每日指标")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertDailyBasic")
    //@SaCheckPermission(value={"v_btn_modify_DailyBasic"})
    public ApiResult<?> insertDailyBasic(
            @RequestBody DailyBasicAddReqVo vo)   {
        dailyBasicServiceApi.insertDailyBasic(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新个股每日指标", description = "更新个股每日指标")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateDailyBasic")
    //@SaCheckPermission(value={"v_btn_modify_DailyBasic"})
    public ApiResult<?> updateDailyBasic(
            @RequestBody DailyBasicEditReqVo vo)   {
        dailyBasicServiceApi.updateDailyBasic(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询个股每日指标详情", description = "查询个股每日指标详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getDailyBasicDetails")
    //@SaCheckPermission(value={"inc_menu_select_DailyBasic"})
    public ApiResult< DailyBasicResVo > getDailyBasicDetails(
            @RequestBody DailyBasicDetailsReqVo vo)   {
        return new  ApiResult<>(dailyBasicServiceApi.selectDailyBasicById(vo));
    }



}
