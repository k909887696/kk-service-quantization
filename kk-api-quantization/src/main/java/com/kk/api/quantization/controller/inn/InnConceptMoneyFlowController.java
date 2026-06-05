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
import com.kk.business.quantization.serviceapi.ConceptMoneyFlowServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.ConceptMoneyFlowListReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptMoneyFlowListResVo;
import com.kk.business.quantization.model.vobase.req.ConceptMoneyFlowAddReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptMoneyFlowEditReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptMoneyFlowResVo;
import com.kk.business.quantization.model.vobase.req.ConceptMoneyFlowDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptMoneyFlowDeleteReqVo;
/**
 * <p>
 * 概念资金流向 内部接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnConceptMoneyFlow",description = "概念资金流向(内部接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnConceptMoneyFlow")
public class InnConceptMoneyFlowController {

    @Resource
    public ConceptMoneyFlowServiceApi conceptMoneyFlowServiceApi;

    @Operation(summary = "获取概念资金流向分页结果集", description = "获取概念资金流向分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getConceptMoneyFlowPageList")
    //@SaCheckPermission(value = {"v_menu_select_ConceptMoneyFlow"})
    public ApiResult<PageResult<ConceptMoneyFlowListResVo>> getConceptMoneyFlowPageList(
            @RequestBody ConceptMoneyFlowListReqVo vo)   {
        return new ApiResult<>(conceptMoneyFlowServiceApi.selectConceptMoneyFlowPageList(vo));
    }
    @Operation(summary = "删除概念资金流向", description = "删除概念资金流向")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteConceptMoneyFlowById")
    //@SaCheckPermission(value={"v_btn_delete_ConceptMoneyFlow"})
    public ApiResult<?> deleteConceptMoneyFlowById(
            @RequestBody ConceptMoneyFlowDeleteReqVo vo)   {
        conceptMoneyFlowServiceApi.deleteConceptMoneyFlowById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入概念资金流向", description = "插入概念资金流向")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertConceptMoneyFlow")
    //@SaCheckPermission(value={"v_btn_modify_ConceptMoneyFlow"})
    public ApiResult<?> insertConceptMoneyFlow(
            @RequestBody ConceptMoneyFlowAddReqVo vo)   {
        conceptMoneyFlowServiceApi.insertConceptMoneyFlow(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新概念资金流向", description = "更新概念资金流向")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateConceptMoneyFlow")
    //@SaCheckPermission(value={"v_btn_modify_ConceptMoneyFlow"})
    public ApiResult<?> updateConceptMoneyFlow(
            @RequestBody ConceptMoneyFlowEditReqVo vo)   {
        conceptMoneyFlowServiceApi.updateConceptMoneyFlow(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询概念资金流向详情", description = "查询概念资金流向详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getConceptMoneyFlowDetails")
    //@SaCheckPermission(value={"inc_menu_select_ConceptMoneyFlow"})
    public ApiResult< ConceptMoneyFlowResVo > getConceptMoneyFlowDetails(
            @RequestBody ConceptMoneyFlowDetailsReqVo vo)   {
        return new  ApiResult<>(conceptMoneyFlowServiceApi.selectConceptMoneyFlowById(vo));
    }



}
