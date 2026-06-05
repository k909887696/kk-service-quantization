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
import com.kk.business.quantization.serviceapi.DailyServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.DailyListReqVo;
import com.kk.business.quantization.model.vobase.res.DailyListResVo;
import com.kk.business.quantization.model.vobase.req.DailyAddReqVo;
import com.kk.business.quantization.model.vobase.req.DailyEditReqVo;
import com.kk.business.quantization.model.vobase.res.DailyResVo;
import com.kk.business.quantization.model.vobase.req.DailyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.DailyDeleteReqVo;
/**
 * <p>
 * 个股日线行情 内部接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnDaily",description = "个股日线行情(内部接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnDaily")
public class InnDailyController {

    @Resource
    public DailyServiceApi dailyServiceApi;

    @Operation(summary = "获取个股日线行情分页结果集", description = "获取个股日线行情分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getDailyPageList")
    //@SaCheckPermission(value = {"v_menu_select_Daily"})
    public ApiResult<PageResult<DailyListResVo>> getDailyPageList(
            @RequestBody DailyListReqVo vo)   {
        return new ApiResult<>(dailyServiceApi.selectDailyPageList(vo));
    }
    @Operation(summary = "删除个股日线行情", description = "删除个股日线行情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteDailyById")
    //@SaCheckPermission(value={"v_btn_delete_Daily"})
    public ApiResult<?> deleteDailyById(
            @RequestBody DailyDeleteReqVo vo)   {
        dailyServiceApi.deleteDailyById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入个股日线行情", description = "插入个股日线行情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertDaily")
    //@SaCheckPermission(value={"v_btn_modify_Daily"})
    public ApiResult<?> insertDaily(
            @RequestBody DailyAddReqVo vo)   {
        dailyServiceApi.insertDaily(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新个股日线行情", description = "更新个股日线行情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateDaily")
    //@SaCheckPermission(value={"v_btn_modify_Daily"})
    public ApiResult<?> updateDaily(
            @RequestBody DailyEditReqVo vo)   {
        dailyServiceApi.updateDaily(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询个股日线行情详情", description = "查询个股日线行情详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getDailyDetails")
    //@SaCheckPermission(value={"inc_menu_select_Daily"})
    public ApiResult< DailyResVo > getDailyDetails(
            @RequestBody DailyDetailsReqVo vo)   {
        return new  ApiResult<>(dailyServiceApi.selectDailyById(vo));
    }



}
