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
import com.kk.business.quantization.serviceapi.MaxPctChgServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.MaxPctChgListReqVo;
import com.kk.business.quantization.model.vobase.res.MaxPctChgListResVo;
import com.kk.business.quantization.model.vobase.req.MaxPctChgAddReqVo;
import com.kk.business.quantization.model.vobase.req.MaxPctChgEditReqVo;
import com.kk.business.quantization.model.vobase.res.MaxPctChgResVo;
import com.kk.business.quantization.model.vobase.req.MaxPctChgDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.MaxPctChgDeleteReqVo;
/**
 * <p>
 * 各个市场涨跌幅限制 内部接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnMaxPctChg",description = "各个市场涨跌幅限制(内部接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnMaxPctChg")
public class InnMaxPctChgController {

    @Resource
    public MaxPctChgServiceApi maxPctChgServiceApi;

    @Operation(summary = "获取各个市场涨跌幅限制分页结果集", description = "获取各个市场涨跌幅限制分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getMaxPctChgPageList")
    //@SaCheckPermission(value = {"v_menu_select_MaxPctChg"})
    public ApiResult<PageResult<MaxPctChgListResVo>> getMaxPctChgPageList(
            @RequestBody MaxPctChgListReqVo vo)   {
        return new ApiResult<>(maxPctChgServiceApi.selectMaxPctChgPageList(vo));
    }
    @Operation(summary = "删除各个市场涨跌幅限制", description = "删除各个市场涨跌幅限制")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteMaxPctChgById")
    //@SaCheckPermission(value={"v_btn_delete_MaxPctChg"})
    public ApiResult<?> deleteMaxPctChgById(
            @RequestBody MaxPctChgDeleteReqVo vo)   {
        maxPctChgServiceApi.deleteMaxPctChgById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入各个市场涨跌幅限制", description = "插入各个市场涨跌幅限制")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertMaxPctChg")
    //@SaCheckPermission(value={"v_btn_modify_MaxPctChg"})
    public ApiResult<?> insertMaxPctChg(
            @RequestBody MaxPctChgAddReqVo vo)   {
        maxPctChgServiceApi.insertMaxPctChg(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新各个市场涨跌幅限制", description = "更新各个市场涨跌幅限制")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateMaxPctChg")
    //@SaCheckPermission(value={"v_btn_modify_MaxPctChg"})
    public ApiResult<?> updateMaxPctChg(
            @RequestBody MaxPctChgEditReqVo vo)   {
        maxPctChgServiceApi.updateMaxPctChg(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询各个市场涨跌幅限制详情", description = "查询各个市场涨跌幅限制详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getMaxPctChgDetails")
    //@SaCheckPermission(value={"inc_menu_select_MaxPctChg"})
    public ApiResult< MaxPctChgResVo > getMaxPctChgDetails(
            @RequestBody MaxPctChgDetailsReqVo vo)   {
        return new  ApiResult<>(maxPctChgServiceApi.selectMaxPctChgById(vo));
    }



}
