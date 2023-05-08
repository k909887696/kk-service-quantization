package com.kk.api.quantization.controller;


import com.kk.business.quantization.dao.entity.IndexDaily;
import com.kk.business.quantization.service.IIndexDailyService;
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
 * 指数日线行情 前端控制器
 * </p>
 *
 * @author kk
 * @since 2021-12-27
 */
 @Api(value = "/quantization/api/v1/index_daily",tags = "指数日线行情")
@ResponseBody
@RestController
@RequestMapping("/quantization/api/v1/index_daily")
public class IndexDailyController {

    @Resource
    public IIndexDailyService service;

    @ApiOperation("获取分页结果集")
    @ApiImplicitParams(  {
    @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header",  dataType = "String")
    })
    @PostMapping("/get_page_result")
    public ApiResult< PageResult<IndexDaily> > getPageResult(@Valid @RequestBody BasePage vo)   {

        return new  ApiResult(service.getPageResult(vo));

    }

}