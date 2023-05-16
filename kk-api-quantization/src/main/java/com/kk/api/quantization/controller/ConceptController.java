package com.kk.api.quantization.controller;


import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import com.kk.business.quantization.service.IConceptService;
import com.kk.common.web.model.ApiResult;
import com.kk.common.base.model.PageResult;
import com.kk.business.quantization.model.vo.ConceptListVo;
import com.kk.business.quantization.model.dto.ConceptListDto;
import com.kk.business.quantization.model.vo.ConceptAddVo;
import com.kk.business.quantization.model.vo.ConceptEditVo;
import com.kk.business.quantization.model.vo.ConceptDetailsVo;
import com.kk.business.quantization.model.vo.ConceptDeleteVo;
import com.kk.business.quantization.model.dto.ConceptDto;
import com.kk.business.quantization.dao.entity.Concept;
/**
 * <p>
 * 概念分类 前端控制器
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
@Api(value = "/quantization/api/v1/concept",tags = "概念分类")
@ResponseBody
@RestController
@RequestMapping("/quantization/api/v1/concept")
public class ConceptController {

    @Resource
    public IConceptService conceptService;

    @ApiOperation("获取分页结果集")
    @ApiImplicitParams(  {
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header",  dataType = "String")
    })
    @PostMapping("/get_concept_page_list")
    public ApiResult< PageResult<ConceptListDto> > getConceptPageList(@Valid @RequestBody ConceptListVo vo)   {

        return new  ApiResult(conceptService.selectPageList(vo));

    }
    @ApiOperation("删除")
    @ApiImplicitParams(  {
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header",  dataType = "String")
    })
    @PostMapping("/delete")
    public ApiResult<?> getConceptPageList(@Valid @RequestBody ConceptDeleteVo vo)   {
        conceptService.deleteById(vo);
        return ApiResult.SUCCESS;

    }
    @ApiOperation("插入")
    @ApiImplicitParams(  {
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header",  dataType = "String")
    })
    @PostMapping("/insert")
    public ApiResult<?> insert(@Valid @RequestBody ConceptAddVo vo)   {
        conceptService.insert(vo);
        return ApiResult.SUCCESS;

    }
    @ApiOperation("更新")
    @ApiImplicitParams(  {
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header",  dataType = "String")
    })
    @PostMapping("/update")
    public ApiResult<?> update(@Valid @RequestBody ConceptEditVo vo)   {
        conceptService.update(vo);
        return ApiResult.SUCCESS;

    }
    @ApiOperation("查询详情")
    @ApiImplicitParams(  {
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header",  dataType = "String")
    })
    @PostMapping("/get_details")
    public ApiResult< ConceptDto > getDetails(@Valid @RequestBody ConceptDetailsVo vo)   {

        return new  ApiResult(conceptService.selectById(vo));

    }



}
