package com.kk.api.quantization.controller;


import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import com.kk.business.quantization.service.IStockDayKdjService;
import com.kk.common.web.model.ApiResult;
import com.kk.common.base.model.PageResult;
import com.kk.business.quantization.model.vo.StockDayKdjListVo;
import com.kk.business.quantization.model.dto.StockDayKdjListDto;
import com.kk.business.quantization.model.vo.StockDayKdjAddVo;
import com.kk.business.quantization.model.vo.StockDayKdjEditVo;
import com.kk.business.quantization.model.vo.StockDayKdjDetailsVo;
import com.kk.business.quantization.model.vo.StockDayKdjDeleteVo;
import com.kk.business.quantization.model.dto.StockDayKdjDto;
import com.kk.business.quantization.dao.entity.StockDayKdj;
/**
 * <p>
 * 个股kdj数据 前端控制器
 * </p>
 *
 * @author kk
 * @since 2023-05-18
 */
@Api(value = "/quantization/api/v1/stock_day_kdj",tags = "个股kdj数据")
@ResponseBody
@RestController
@RequestMapping("/quantization/api/v1/stock_day_kdj")
public class StockDayKdjController {

    @Resource
    public IStockDayKdjService stockDayKdjService;

    @ApiOperation("获取分页结果集")
    @ApiImplicitParams(  {
    @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header",  dataType = "String")
    })
    @PostMapping("/get_stock_day_kdj_page_list")
    public ApiResult< PageResult<StockDayKdjListDto> > getStockDayKdjPageList(@Valid @RequestBody StockDayKdjListVo vo)   {

        return new  ApiResult(stockDayKdjService.selectPageList(vo));

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
    public ApiResult<?> deleteById(@Valid @RequestBody StockDayKdjDeleteVo vo)   {
        stockDayKdjService.deleteById(vo);
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
    public ApiResult<?> insert(@Valid @RequestBody StockDayKdjAddVo vo)   {
        stockDayKdjService.insert(vo);
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
    public ApiResult<?> update(@Valid @RequestBody StockDayKdjEditVo vo)   {
        stockDayKdjService.update(vo);
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
    public ApiResult< StockDayKdjDto > getDetails(@Valid @RequestBody StockDayKdjDetailsVo vo)   {

        return new  ApiResult(stockDayKdjService.selectById(vo));

    }



}
