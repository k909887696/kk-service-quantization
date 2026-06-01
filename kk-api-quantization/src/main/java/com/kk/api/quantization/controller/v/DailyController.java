package com.kk.api.quantization.controller.v;


import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
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
@Tag(name = "/"+ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin+"/v1/daily",description = "个股日线行情")
@ResponseBody
@RestController
@RequestMapping("/"+ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin+"/v1/daily")
public class DailyController {

    @Resource
    public IDailyService dailyService;

    @Operation(summary = "获取分页结果集")
    @Parameters(  {
            @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/get_daily_page_list")
    public ApiResult< PageResult<DailyListDto> > getDailyPageList(@Valid @RequestBody DailyListVo vo)   {

        return new  ApiResult(dailyService.selectPageList(vo));

    }
    @Operation(summary = "删除")
    @Parameters(  {
            @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/delete_by_id")
    public ApiResult<?> deleteById(@Valid @RequestBody DailyDeleteVo vo)   {
        dailyService.deleteById(vo);
        return ApiResult.SUCCESS;

    }
    @Operation(summary = "插入")
    @Parameters(  {
            @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insert")
    public ApiResult<?> insert(@Valid @RequestBody DailyAddVo vo)   {
        dailyService.insert(vo);
        return ApiResult.SUCCESS;

    }
    @Operation(summary = "更新")
    @Parameters(  {
            @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/update")
    public ApiResult<?> update(@Valid @RequestBody DailyEditVo vo)   {
        dailyService.update(vo);
        return ApiResult.SUCCESS;

    }
    @Operation(summary = "查询详情")
    @Parameters(  {
            @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/get_details")
    public ApiResult< DailyDto > getDetails(@Valid @RequestBody DailyDetailsVo vo)   {

        return new  ApiResult(dailyService.selectById(vo));

    }



}
