package com.kk.api.quantization.controller;


import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import com.kk.business.quantization.service.IConceptMoneyFlowService;
import com.kk.common.web.model.ApiResult;
import com.kk.common.base.model.PageResult;
import com.kk.business.quantization.model.vo.ConceptMoneyFlowListVo;
import com.kk.business.quantization.model.dto.ConceptMoneyFlowListDto;
import com.kk.business.quantization.model.vo.ConceptMoneyFlowAddVo;
import com.kk.business.quantization.model.vo.ConceptMoneyFlowEditVo;
import com.kk.business.quantization.model.vo.ConceptMoneyFlowDetailsVo;
import com.kk.business.quantization.model.vo.ConceptMoneyFlowDeleteVo;
import com.kk.business.quantization.model.dto.ConceptMoneyFlowDto;
import com.kk.business.quantization.dao.entity.ConceptMoneyFlow;
/**
 * <p>
 * 概念资金流向 前端控制器
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
@Api(value = "/quantization/api/v1/concept_money_flow",tags = "概念资金流向")
@ResponseBody
@RestController
@RequestMapping("/quantization/api/v1/concept_money_flow")
public class ConceptMoneyFlowController {

    @Resource
    public IConceptMoneyFlowService conceptMoneyFlowService;

    @ApiOperation("获取分页结果集")
    @ApiImplicitParams(  {
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header",  dataType = "String")
    })
    @PostMapping("/get_concept_money_flow_page_list")
    public ApiResult< PageResult<ConceptMoneyFlowListDto> > getConceptMoneyFlowPageList(@Valid @RequestBody ConceptMoneyFlowListVo vo)   {

        return new  ApiResult(conceptMoneyFlowService.selectPageList(vo));

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
    public ApiResult<?> getConceptMoneyFlowPageList(@Valid @RequestBody ConceptMoneyFlowDeleteVo vo)   {
        conceptMoneyFlowService.deleteById(vo);
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
    public ApiResult<?> insert(@Valid @RequestBody ConceptMoneyFlowAddVo vo)   {
        conceptMoneyFlowService.insert(vo);
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
    public ApiResult<?> update(@Valid @RequestBody ConceptMoneyFlowEditVo vo)   {
        conceptMoneyFlowService.update(vo);
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
    public ApiResult< ConceptMoneyFlowDto > getDetails(@Valid @RequestBody ConceptMoneyFlowDetailsVo vo)   {

        return new  ApiResult(conceptMoneyFlowService.selectById(vo));

    }



}
