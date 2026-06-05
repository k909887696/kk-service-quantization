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
import com.kk.business.quantization.serviceapi.MoneyFlowServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.MoneyFlowListReqVo;
import com.kk.business.quantization.model.vobase.res.MoneyFlowListResVo;
import com.kk.business.quantization.model.vobase.req.MoneyFlowAddReqVo;
import com.kk.business.quantization.model.vobase.req.MoneyFlowEditReqVo;
import com.kk.business.quantization.model.vobase.res.MoneyFlowResVo;
import com.kk.business.quantization.model.vobase.req.MoneyFlowDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.MoneyFlowDeleteReqVo;
/**
 * <p>
 * 个股资金流向 内部接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnMoneyFlow",description = "个股资金流向(内部接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixInn + "/v1/InnMoneyFlow")
public class InnMoneyFlowController {

    @Resource
    public MoneyFlowServiceApi moneyFlowServiceApi;

    @Operation(summary = "获取个股资金流向分页结果集", description = "获取个股资金流向分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getMoneyFlowPageList")
    //@SaCheckPermission(value = {"v_menu_select_MoneyFlow"})
    public ApiResult<PageResult<MoneyFlowListResVo>> getMoneyFlowPageList(
            @RequestBody MoneyFlowListReqVo vo)   {
        return new ApiResult<>(moneyFlowServiceApi.selectMoneyFlowPageList(vo));
    }
    @Operation(summary = "删除个股资金流向", description = "删除个股资金流向")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteMoneyFlowById")
    //@SaCheckPermission(value={"v_btn_delete_MoneyFlow"})
    public ApiResult<?> deleteMoneyFlowById(
            @RequestBody MoneyFlowDeleteReqVo vo)   {
        moneyFlowServiceApi.deleteMoneyFlowById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入个股资金流向", description = "插入个股资金流向")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertMoneyFlow")
    //@SaCheckPermission(value={"v_btn_modify_MoneyFlow"})
    public ApiResult<?> insertMoneyFlow(
            @RequestBody MoneyFlowAddReqVo vo)   {
        moneyFlowServiceApi.insertMoneyFlow(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新个股资金流向", description = "更新个股资金流向")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateMoneyFlow")
    //@SaCheckPermission(value={"v_btn_modify_MoneyFlow"})
    public ApiResult<?> updateMoneyFlow(
            @RequestBody MoneyFlowEditReqVo vo)   {
        moneyFlowServiceApi.updateMoneyFlow(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询个股资金流向详情", description = "查询个股资金流向详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getMoneyFlowDetails")
    //@SaCheckPermission(value={"inc_menu_select_MoneyFlow"})
    public ApiResult< MoneyFlowResVo > getMoneyFlowDetails(
            @RequestBody MoneyFlowDetailsReqVo vo)   {
        return new  ApiResult<>(moneyFlowServiceApi.selectMoneyFlowById(vo));
    }



}
