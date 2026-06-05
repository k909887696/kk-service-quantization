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
import com.kk.business.quantization.serviceapi.WeeklyServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.WeeklyListReqVo;
import com.kk.business.quantization.model.vobase.res.WeeklyListResVo;
import com.kk.business.quantization.model.vobase.req.WeeklyAddReqVo;
import com.kk.business.quantization.model.vobase.req.WeeklyEditReqVo;
import com.kk.business.quantization.model.vobase.res.WeeklyResVo;
import com.kk.business.quantization.model.vobase.req.WeeklyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.WeeklyDeleteReqVo;
/**
 * <p>
 * 个股周线行情 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/VWeekly",description = "个股周线行情(接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/VWeekly")
public class VWeeklyController {

    @Resource
    public WeeklyServiceApi weeklyServiceApi;

    @Operation(summary = "获取个股周线行情分页结果集", description = "获取个股周线行情分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getWeeklyPageList")
    //@SaCheckPermission(value = {"v_menu_select_Weekly"})
    public ApiResult<PageResult<WeeklyListResVo>> getWeeklyPageList(
            @RequestBody WeeklyListReqVo vo)   {
        return new ApiResult<>(weeklyServiceApi.selectWeeklyPageList(vo));
    }
    @Operation(summary = "删除个股周线行情", description = "删除个股周线行情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteWeeklyById")
    //@SaCheckPermission(value={"v_btn_delete_Weekly"})
    public ApiResult<?> deleteWeeklyById(
            @RequestBody WeeklyDeleteReqVo vo)   {
        weeklyServiceApi.deleteWeeklyById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入个股周线行情", description = "插入个股周线行情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertWeekly")
    //@SaCheckPermission(value={"v_btn_modify_Weekly"})
    public ApiResult<?> insertWeekly(
            @RequestBody WeeklyAddReqVo vo)   {
        weeklyServiceApi.insertWeekly(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新个股周线行情", description = "更新个股周线行情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateWeekly")
    //@SaCheckPermission(value={"v_btn_modify_Weekly"})
    public ApiResult<?> updateWeekly(
            @RequestBody WeeklyEditReqVo vo)   {
        weeklyServiceApi.updateWeekly(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询个股周线行情详情", description = "查询个股周线行情详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getWeeklyDetails")
    //@SaCheckPermission(value={"inc_menu_select_Weekly"})
    public ApiResult< WeeklyResVo > getWeeklyDetails(
            @RequestBody WeeklyDetailsReqVo vo)   {
        return new  ApiResult<>(weeklyServiceApi.selectWeeklyById(vo));
    }



}
