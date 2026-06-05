package com.kk.api.quantization.controller.ext;


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
import com.kk.business.quantization.serviceapi.CnMServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.CnMListReqVo;
import com.kk.business.quantization.model.vobase.res.CnMListResVo;
import com.kk.business.quantization.model.vobase.req.CnMAddReqVo;
import com.kk.business.quantization.model.vobase.req.CnMEditReqVo;
import com.kk.business.quantization.model.vobase.res.CnMResVo;
import com.kk.business.quantization.model.vobase.req.CnMDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.CnMDeleteReqVo;
/**
 * <p>
 * 人民币货币总量对象 外部接口
 * </p>
 *
 * @author kk
 * @since 2026-06-02
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixSign + "/v1/ExtCnM",description = "人民币货币总量对象(外部接口-签名认证)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixSign + "/v1/ExtCnM")
public class ExtCnMController {

    @Resource
    public CnMServiceApi cnMServiceApi;

    @Operation(summary = "获取人民币货币总量对象分页结果集", description = "获取人民币货币总量对象分页结果集")
    @Parameters(  {
        @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getCnMPageList")
    //@SaCheckPermission(value = {"v_menu_select_CnM"})
    public ApiResult<PageResult<CnMListResVo>> getCnMPageList(
            @RequestBody CnMListReqVo vo)   {
        return new ApiResult<>(cnMServiceApi.selectCnMPageList(vo));
    }
    @Operation(summary = "删除人民币货币总量对象", description = "删除人民币货币总量对象")
    @Parameters(  {
        @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteCnMById")
    //@SaCheckPermission(value={"v_btn_delete_CnM"})
    public ApiResult<?> deleteCnMById(
            @RequestBody CnMDeleteReqVo vo)   {
        cnMServiceApi.deleteCnMById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入人民币货币总量对象", description = "插入人民币货币总量对象")
    @Parameters(  {
        @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertCnM")
    //@SaCheckPermission(value={"v_btn_modify_CnM"})
    public ApiResult<?> insertCnM(
            @RequestBody CnMAddReqVo vo)   {
        cnMServiceApi.insertCnM(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新人民币货币总量对象", description = "更新人民币货币总量对象")
    @Parameters(  {
        @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateCnM")
    //@SaCheckPermission(value={"v_btn_modify_CnM"})
    public ApiResult<?> updateCnM(
            @RequestBody CnMEditReqVo vo)   {
        cnMServiceApi.updateCnM(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询人民币货币总量对象详情", description = "查询人民币货币总量对象详情")
    @Parameters(  {
        @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getCnMDetails")
    //@SaCheckPermission(value={"inc_menu_select_CnM"})
    public ApiResult< CnMResVo > getCnMDetails(
            @RequestBody CnMDetailsReqVo vo)   {
        return new  ApiResult<>(cnMServiceApi.selectCnMById(vo));
    }

}
