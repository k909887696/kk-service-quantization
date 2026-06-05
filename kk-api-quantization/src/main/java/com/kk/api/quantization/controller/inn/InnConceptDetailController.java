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
import com.kk.business.quantization.serviceapi.ConceptDetailServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.ConceptDetailListReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptDetailListResVo;
import com.kk.business.quantization.model.vobase.req.ConceptDetailAddReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptDetailEditReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptDetailResVo;
import com.kk.business.quantization.model.vobase.req.ConceptDetailDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptDetailDeleteReqVo;
/**
 * <p>
 * 概念明细 内部接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnConceptDetail",description = "概念明细(内部接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnConceptDetail")
public class InnConceptDetailController {

    @Resource
    public ConceptDetailServiceApi conceptDetailServiceApi;

    @Operation(summary = "获取概念明细分页结果集", description = "获取概念明细分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getConceptDetailPageList")
    //@SaCheckPermission(value = {"v_menu_select_ConceptDetail"})
    public ApiResult<PageResult<ConceptDetailListResVo>> getConceptDetailPageList(
            @RequestBody ConceptDetailListReqVo vo)   {
        return new ApiResult<>(conceptDetailServiceApi.selectConceptDetailPageList(vo));
    }
    @Operation(summary = "删除概念明细", description = "删除概念明细")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteConceptDetailById")
    //@SaCheckPermission(value={"v_btn_delete_ConceptDetail"})
    public ApiResult<?> deleteConceptDetailById(
            @RequestBody ConceptDetailDeleteReqVo vo)   {
        conceptDetailServiceApi.deleteConceptDetailById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入概念明细", description = "插入概念明细")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertConceptDetail")
    //@SaCheckPermission(value={"v_btn_modify_ConceptDetail"})
    public ApiResult<?> insertConceptDetail(
            @RequestBody ConceptDetailAddReqVo vo)   {
        conceptDetailServiceApi.insertConceptDetail(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新概念明细", description = "更新概念明细")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateConceptDetail")
    //@SaCheckPermission(value={"v_btn_modify_ConceptDetail"})
    public ApiResult<?> updateConceptDetail(
            @RequestBody ConceptDetailEditReqVo vo)   {
        conceptDetailServiceApi.updateConceptDetail(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询概念明细详情", description = "查询概念明细详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getConceptDetailDetails")
    //@SaCheckPermission(value={"inc_menu_select_ConceptDetail"})
    public ApiResult< ConceptDetailResVo > getConceptDetailDetails(
            @RequestBody ConceptDetailDetailsReqVo vo)   {
        return new  ApiResult<>(conceptDetailServiceApi.selectConceptDetailById(vo));
    }



}
