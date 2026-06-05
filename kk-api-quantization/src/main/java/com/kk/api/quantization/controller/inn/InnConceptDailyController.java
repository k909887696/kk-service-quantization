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
import com.kk.business.quantization.serviceapi.ConceptDailyServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.ConceptDailyListReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptDailyListResVo;
import com.kk.business.quantization.model.vobase.req.ConceptDailyAddReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptDailyEditReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptDailyResVo;
import com.kk.business.quantization.model.vobase.req.ConceptDailyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptDailyDeleteReqVo;
/**
 * <p>
 * 概念日线行情 内部接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnConceptDaily",description = "概念日线行情(内部接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnConceptDaily")
public class InnConceptDailyController {

    @Resource
    public ConceptDailyServiceApi conceptDailyServiceApi;

    @Operation(summary = "获取概念日线行情分页结果集", description = "获取概念日线行情分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getConceptDailyPageList")
    //@SaCheckPermission(value = {"v_menu_select_ConceptDaily"})
    public ApiResult<PageResult<ConceptDailyListResVo>> getConceptDailyPageList(
            @RequestBody ConceptDailyListReqVo vo)   {
        return new ApiResult<>(conceptDailyServiceApi.selectConceptDailyPageList(vo));
    }
    @Operation(summary = "删除概念日线行情", description = "删除概念日线行情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteConceptDailyById")
    //@SaCheckPermission(value={"v_btn_delete_ConceptDaily"})
    public ApiResult<?> deleteConceptDailyById(
            @RequestBody ConceptDailyDeleteReqVo vo)   {
        conceptDailyServiceApi.deleteConceptDailyById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入概念日线行情", description = "插入概念日线行情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertConceptDaily")
    //@SaCheckPermission(value={"v_btn_modify_ConceptDaily"})
    public ApiResult<?> insertConceptDaily(
            @RequestBody ConceptDailyAddReqVo vo)   {
        conceptDailyServiceApi.insertConceptDaily(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新概念日线行情", description = "更新概念日线行情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateConceptDaily")
    //@SaCheckPermission(value={"v_btn_modify_ConceptDaily"})
    public ApiResult<?> updateConceptDaily(
            @RequestBody ConceptDailyEditReqVo vo)   {
        conceptDailyServiceApi.updateConceptDaily(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询概念日线行情详情", description = "查询概念日线行情详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getConceptDailyDetails")
    //@SaCheckPermission(value={"inc_menu_select_ConceptDaily"})
    public ApiResult< ConceptDailyResVo > getConceptDailyDetails(
            @RequestBody ConceptDailyDetailsReqVo vo)   {
        return new  ApiResult<>(conceptDailyServiceApi.selectConceptDailyById(vo));
    }



}
