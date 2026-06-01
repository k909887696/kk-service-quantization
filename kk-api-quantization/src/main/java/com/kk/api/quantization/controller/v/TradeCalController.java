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
import com.kk.business.quantization.service.ITradeCalService;
import com.kk.common.web.model.ApiResult;
import com.kk.common.base.model.PageResult;
import com.kk.business.quantization.model.vo.TradeCalListVo;
import com.kk.business.quantization.model.dto.TradeCalListDto;
import com.kk.business.quantization.model.vo.TradeCalAddVo;
import com.kk.business.quantization.model.vo.TradeCalEditVo;
import com.kk.business.quantization.model.vo.TradeCalDetailsVo;
import com.kk.business.quantization.model.vo.TradeCalDeleteVo;
import com.kk.business.quantization.model.dto.TradeCalDto;
import com.kk.business.quantization.dao.entity.TradeCal;
/**
 * <p>
 * 交易日历 前端控制器
 * </p>
 *
 * @author kk
 * @since 2023-05-18
 */
@Tag(name = "/"+ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin+"/v1/trade_cal",description = "交易日历")
@ResponseBody
@RestController
@RequestMapping("/"+ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin+"/v1/trade_cal")
public class TradeCalController {

    @Resource
    public ITradeCalService tradeCalService;

    @Operation(summary = "获取分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
})
    @PostMapping("/get_trade_cal_page_list")
    public ApiResult< PageResult<TradeCalListDto> > getTradeCalPageList(@Valid @RequestBody TradeCalListVo vo)   {

        return new  ApiResult(tradeCalService.selectPageList(vo));

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
    public ApiResult<?> deleteById(@Valid @RequestBody TradeCalDeleteVo vo)   {
        tradeCalService.deleteById(vo);
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
    public ApiResult<?> insert(@Valid @RequestBody TradeCalAddVo vo)   {
        tradeCalService.insert(vo);
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
    public ApiResult<?> update(@Valid @RequestBody TradeCalEditVo vo)   {
        tradeCalService.update(vo);
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
    public ApiResult< TradeCalDto > getDetails(@Valid @RequestBody TradeCalDetailsVo vo)   {

        return new  ApiResult(tradeCalService.selectById(vo));

    }



}
