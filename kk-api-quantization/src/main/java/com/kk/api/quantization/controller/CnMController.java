package com.kk.api.quantization.controller;


import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import com.kk.business.quantization.service.ICnMService;
import com.kk.common.web.model.ApiResult;
import com.kk.common.base.model.PageResult;
import com.kk.business.quantization.model.vo.CnMListVo;
import com.kk.business.quantization.model.dto.CnMListDto;
import com.kk.business.quantization.model.vo.CnMAddVo;
import com.kk.business.quantization.model.vo.CnMEditVo;
import com.kk.business.quantization.model.vo.CnMDetailsVo;
import com.kk.business.quantization.model.vo.CnMDeleteVo;
import com.kk.business.quantization.model.dto.CnMDto;
import com.kk.business.quantization.dao.entity.CnM;
/**
 * <p>
 * 人民币货币总量对象 前端控制器
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
@Api(value = "/quantization/api/v1/cn_m",tags = "人民币货币总量对象")
@ResponseBody
@RestController
@RequestMapping("/quantization/api/v1/cn_m")
public class CnMController {

    @Resource
    public ICnMService cnMService;

    @ApiOperation("获取分页结果集")
    @ApiImplicitParams(  {
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header",  dataType = "String")
    })
    @PostMapping("/get_cn_m_page_list")
    public ApiResult< PageResult<CnMListDto> > getCnMPageList(@Valid @RequestBody CnMListVo vo)   {

        return new  ApiResult(cnMService.selectPageList(vo));

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
    public ApiResult<?> getCnMPageList(@Valid @RequestBody CnMDeleteVo vo)   {
        cnMService.deleteById(vo);
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
    public ApiResult<?> insert(@Valid @RequestBody CnMAddVo vo)   {
        cnMService.insert(vo);
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
    public ApiResult<?> update(@Valid @RequestBody CnMEditVo vo)   {
        cnMService.update(vo);
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
    public ApiResult< CnMDto > getDetails(@Valid @RequestBody CnMDetailsVo vo)   {

        return new  ApiResult(cnMService.selectById(vo));

    }



}
