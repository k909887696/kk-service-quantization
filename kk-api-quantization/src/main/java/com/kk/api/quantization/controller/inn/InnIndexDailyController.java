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
import com.kk.business.quantization.serviceapi.IndexDailyServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.IndexDailyListReqVo;
import com.kk.business.quantization.model.vobase.res.IndexDailyListResVo;
import com.kk.business.quantization.model.vobase.req.IndexDailyAddReqVo;
import com.kk.business.quantization.model.vobase.req.IndexDailyEditReqVo;
import com.kk.business.quantization.model.vobase.res.IndexDailyResVo;
import com.kk.business.quantization.model.vobase.req.IndexDailyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.IndexDailyDeleteReqVo;
/**
 * <p>
 * 指数日线行情 内部接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnIndexDaily",description = "指数日线行情(内部接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnIndexDaily")
public class InnIndexDailyController {

    @Resource
    public IndexDailyServiceApi indexDailyServiceApi;

    @Operation(summary = "获取指数日线行情分页结果集", description = "获取指数日线行情分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getIndexDailyPageList")
    //@SaCheckPermission(value = {"v_menu_select_IndexDaily"})
    public ApiResult<PageResult<IndexDailyListResVo>> getIndexDailyPageList(
            @RequestBody IndexDailyListReqVo vo)   {
        return new ApiResult<>(indexDailyServiceApi.selectIndexDailyPageList(vo));
    }
    @Operation(summary = "删除指数日线行情", description = "删除指数日线行情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteIndexDailyById")
    //@SaCheckPermission(value={"v_btn_delete_IndexDaily"})
    public ApiResult<?> deleteIndexDailyById(
            @RequestBody IndexDailyDeleteReqVo vo)   {
        indexDailyServiceApi.deleteIndexDailyById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入指数日线行情", description = "插入指数日线行情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertIndexDaily")
    //@SaCheckPermission(value={"v_btn_modify_IndexDaily"})
    public ApiResult<?> insertIndexDaily(
            @RequestBody IndexDailyAddReqVo vo)   {
        indexDailyServiceApi.insertIndexDaily(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新指数日线行情", description = "更新指数日线行情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateIndexDaily")
    //@SaCheckPermission(value={"v_btn_modify_IndexDaily"})
    public ApiResult<?> updateIndexDaily(
            @RequestBody IndexDailyEditReqVo vo)   {
        indexDailyServiceApi.updateIndexDaily(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询指数日线行情详情", description = "查询指数日线行情详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getIndexDailyDetails")
    //@SaCheckPermission(value={"inc_menu_select_IndexDaily"})
    public ApiResult< IndexDailyResVo > getIndexDailyDetails(
            @RequestBody IndexDailyDetailsReqVo vo)   {
        return new  ApiResult<>(indexDailyServiceApi.selectIndexDailyById(vo));
    }



}
