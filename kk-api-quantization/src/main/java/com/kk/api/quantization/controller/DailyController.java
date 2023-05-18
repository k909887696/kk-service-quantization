package com.kk.api.quantization.controller;


import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import com.kk.business.quantization.service.IDailyService;
import com.kk.common.web.model.ApiResult;
import com.kk.common.base.model.PageResult;
import com.kk.business.quantization.model.vo.DailyListVo;
import com.kk.business.quantization.model.dto.DailyListDto;
import com.kk.business.quantization.model.vo.DailyAddVo;
import com.kk.business.quantization.model.vo.DailyEditVo;
import com.kk.business.quantization.model.vo.DailyDetailsVo;
import com.kk.business.quantization.model.vo.DailyDeleteVo;
import com.kk.business.quantization.model.dto.DailyDto;
import com.kk.business.quantization.dao.entity.Daily;
/**
 * <p>
 * 个股日线行情 前端控制器
 * </p>
 *
 * @author kk
 * @since 2023-05-18
 */
@Api(value = "/quantization/api/v1/daily",tags = "个股日线行情")
@ResponseBody
@RestController
@RequestMapping("/quantization/api/v1/daily")
public class DailyController {

    @Resource
    public IDailyService dailyService;

    @ApiOperation("获取分页结果集")
    @ApiImplicitParams(  {
    @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header",  dataType = "String")
    })
    @PostMapping("/get_daily_page_list")
    public ApiResult< PageResult<DailyListDto> > getDailyPageList(@Valid @RequestBody DailyListVo vo)   {

        return new  ApiResult(dailyService.selectPageList(vo));

    }
    @ApiOperation("删除")
    @ApiImplicitParams(  {
    @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header",  dataType = "String")
    })
    @PostMapping("/delete_by_id")
    public ApiResult<?> deleteById(@Valid @RequestBody DailyDeleteVo vo)   {
        dailyService.deleteById(vo);
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
    public ApiResult<?> insert(@Valid @RequestBody DailyAddVo vo)   {
        dailyService.insert(vo);
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
    public ApiResult<?> update(@Valid @RequestBody DailyEditVo vo)   {
        dailyService.update(vo);
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
    public ApiResult< DailyDto > getDetails(@Valid @RequestBody DailyDetailsVo vo)   {

        return new  ApiResult(dailyService.selectById(vo));

    }



}
