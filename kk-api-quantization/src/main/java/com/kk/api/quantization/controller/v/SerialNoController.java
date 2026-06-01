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
import com.kk.business.quantization.service.ISerialNoService;
import com.kk.common.web.model.ApiResult;
import com.kk.common.base.model.PageResult;
import com.kk.business.quantization.model.vo.SerialNoListVo;
import com.kk.business.quantization.model.dto.SerialNoListDto;
import com.kk.business.quantization.model.vo.SerialNoAddVo;
import com.kk.business.quantization.model.vo.SerialNoEditVo;
import com.kk.business.quantization.model.vo.SerialNoDetailsVo;
import com.kk.business.quantization.model.vo.SerialNoDeleteVo;
import com.kk.business.quantization.model.dto.SerialNoDto;
import com.kk.business.quantization.dao.entity.SerialNo;
/**
 * <p>
 * 自定义主键序号 前端控制器
 * </p>
 *
 * @author kk
 * @since 2023-05-18
 */
@Tag(name = "/"+ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin+"/v1/serial_no",description = "自定义主键序号")
@ResponseBody
@RestController
@RequestMapping("/"+ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin+"/v1/serial_no")
public class SerialNoController {

    @Resource
    public ISerialNoService serialNoService;

    @Operation(summary = "获取分页结果集")
    @Parameters(  {
            @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/get_serial_no_page_list")
    public ApiResult< PageResult<SerialNoListDto> > getSerialNoPageList(@Valid @RequestBody SerialNoListVo vo)   {

        return new  ApiResult(serialNoService.selectPageList(vo));

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
    public ApiResult<?> deleteById(@Valid @RequestBody SerialNoDeleteVo vo)   {
        serialNoService.deleteById(vo);
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
    public ApiResult<?> insert(@Valid @RequestBody SerialNoAddVo vo)   {
        serialNoService.insert(vo);
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
    public ApiResult<?> update(@Valid @RequestBody SerialNoEditVo vo)   {
        serialNoService.update(vo);
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
    public ApiResult< SerialNoDto > getDetails(@Valid @RequestBody SerialNoDetailsVo vo)   {

        return new  ApiResult(serialNoService.selectById(vo));

    }



}
