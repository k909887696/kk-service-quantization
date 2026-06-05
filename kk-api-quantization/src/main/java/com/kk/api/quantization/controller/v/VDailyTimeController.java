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
import com.kk.business.quantization.serviceapi.DailyTimeServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.DailyTimeListReqVo;
import com.kk.business.quantization.model.vobase.res.DailyTimeListResVo;
import com.kk.business.quantization.model.vobase.req.DailyTimeAddReqVo;
import com.kk.business.quantization.model.vobase.req.DailyTimeEditReqVo;
import com.kk.business.quantization.model.vobase.res.DailyTimeResVo;
import com.kk.business.quantization.model.vobase.req.DailyTimeDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.DailyTimeDeleteReqVo;
/**
 * <p>
 * 个股分钟行情 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/VDailyTime",description = "个股分钟行情(接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/VDailyTime")
public class VDailyTimeController {

    @Resource
    public DailyTimeServiceApi dailyTimeServiceApi;

    @Operation(summary = "获取个股分钟行情分页结果集", description = "获取个股分钟行情分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getDailyTimePageList")
    //@SaCheckPermission(value = {"v_menu_select_DailyTime"})
    public ApiResult<PageResult<DailyTimeListResVo>> getDailyTimePageList(
            @RequestBody DailyTimeListReqVo vo)   {
        return new ApiResult<>(dailyTimeServiceApi.selectDailyTimePageList(vo));
    }
    @Operation(summary = "删除个股分钟行情", description = "删除个股分钟行情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteDailyTimeById")
    //@SaCheckPermission(value={"v_btn_delete_DailyTime"})
    public ApiResult<?> deleteDailyTimeById(
            @RequestBody DailyTimeDeleteReqVo vo)   {
        dailyTimeServiceApi.deleteDailyTimeById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入个股分钟行情", description = "插入个股分钟行情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertDailyTime")
    //@SaCheckPermission(value={"v_btn_modify_DailyTime"})
    public ApiResult<?> insertDailyTime(
            @RequestBody DailyTimeAddReqVo vo)   {
        dailyTimeServiceApi.insertDailyTime(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新个股分钟行情", description = "更新个股分钟行情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateDailyTime")
    //@SaCheckPermission(value={"v_btn_modify_DailyTime"})
    public ApiResult<?> updateDailyTime(
            @RequestBody DailyTimeEditReqVo vo)   {
        dailyTimeServiceApi.updateDailyTime(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询个股分钟行情详情", description = "查询个股分钟行情详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getDailyTimeDetails")
    //@SaCheckPermission(value={"inc_menu_select_DailyTime"})
    public ApiResult< DailyTimeResVo > getDailyTimeDetails(
            @RequestBody DailyTimeDetailsReqVo vo)   {
        return new  ApiResult<>(dailyTimeServiceApi.selectDailyTimeById(vo));
    }



}
