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
import com.kk.business.quantization.serviceapi.ConceptServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.ConceptListReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptListResVo;
import com.kk.business.quantization.model.vobase.req.ConceptAddReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptEditReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptResVo;
import com.kk.business.quantization.model.vobase.req.ConceptDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptDeleteReqVo;
/**
 * <p>
 * 概念分类 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/VConcept",description = "概念分类(接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/VConcept")
public class VConceptController {

    @Resource
    public ConceptServiceApi conceptServiceApi;

    @Operation(summary = "获取概念分类分页结果集", description = "获取概念分类分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getConceptPageList")
    //@SaCheckPermission(value = {"v_menu_select_Concept"})
    public ApiResult<PageResult<ConceptListResVo>> getConceptPageList(
            @RequestBody ConceptListReqVo vo)   {
        return new ApiResult<>(conceptServiceApi.selectConceptPageList(vo));
    }
    @Operation(summary = "删除概念分类", description = "删除概念分类")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteConceptById")
    //@SaCheckPermission(value={"v_btn_delete_Concept"})
    public ApiResult<?> deleteConceptById(
            @RequestBody ConceptDeleteReqVo vo)   {
        conceptServiceApi.deleteConceptById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入概念分类", description = "插入概念分类")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertConcept")
    //@SaCheckPermission(value={"v_btn_modify_Concept"})
    public ApiResult<?> insertConcept(
            @RequestBody ConceptAddReqVo vo)   {
        conceptServiceApi.insertConcept(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新概念分类", description = "更新概念分类")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateConcept")
    //@SaCheckPermission(value={"v_btn_modify_Concept"})
    public ApiResult<?> updateConcept(
            @RequestBody ConceptEditReqVo vo)   {
        conceptServiceApi.updateConcept(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询概念分类详情", description = "查询概念分类详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getConceptDetails")
    //@SaCheckPermission(value={"inc_menu_select_Concept"})
    public ApiResult< ConceptResVo > getConceptDetails(
            @RequestBody ConceptDetailsReqVo vo)   {
        return new  ApiResult<>(conceptServiceApi.selectConceptById(vo));
    }



}
