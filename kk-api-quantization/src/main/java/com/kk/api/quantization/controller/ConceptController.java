package com.kk.api.quantization.controller;


import com.kk.business.quantization.dao.entity.Concept;
import com.kk.business.quantization.model.tushare.ConceptVo;
import com.kk.business.quantization.service.IConceptService;
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
 * 股票概念 前端控制器
 * </p>
 *
 * @author kk
 * @since 2021-12-25
 */
 @Api(value = "/quantization/api/v1/concept",tags = "概念分类")
@ResponseBody
@RestController
@RequestMapping("/quantization/api/v1/concept")
public class ConceptController {

    @Resource
    public IConceptService service;

    @ApiOperation("获取分页结果集")
    @ApiImplicitParams(  {
    @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header",  dataType = "String")
    })
    @PostMapping("/get_concept_page_result")
    public ApiResult< PageResult<Concept> > getConceptPageResult(@Valid @RequestBody ConceptVo vo)   {

        return new  ApiResult(service.getConceptPageResult(vo));

    }

}
