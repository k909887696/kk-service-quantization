package com.kk.api.quantization.controller;


import com.kk.business.quantization.dao.entity.ConceptMoneyFlow;
import com.kk.business.quantization.service.IConceptMoneyFlowService;
import com.kk.common.base.model.BasePage;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 概念资金流向 前端控制器
 * </p>
 *
 * @author kk
 * @since 2021-12-25
 */
 @Api(value = "/quantization/api/v1/concept_money_flow",tags = "概念资金流向")
@ResponseBody
@RestController
@RequestMapping("/quantization/api/v1/concept_money_flow")
public class ConceptMoneyFlowController {

    @Resource
    public IConceptMoneyFlowService service;

    @ApiOperation("获取分页结果集")
    @ApiImplicitParams(  {
    @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header",  dataType = "String")
    })
    @PostMapping("/get_concept_money_flow_page_result")
    public ApiResult< PageResult<ConceptMoneyFlow> > getConceptMoneyFlowPageResult(@Valid @RequestBody BasePage vo)   {

        return new  ApiResult(service.getConceptMoneyFlowPageResult(vo));

    }

}
