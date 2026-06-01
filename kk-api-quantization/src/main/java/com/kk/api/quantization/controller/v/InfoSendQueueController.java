package com.kk.api.quantization.controller.v;


import com.kk.business.quantization.dao.entity.InfoSendQueue;
import com.kk.business.quantization.service.IInfoSendQueueService;
import com.kk.common.base.model.BasePage;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * <p>
 * 信息发送队列表 前端控制器
 * </p>
 *
 * @author kk
 * @since 2021-12-29
 */
 @Tag(name = "/"+ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin+"/v1/info_send_queue",description = "信息发送队列表")
@ResponseBody
@RestController
@RequestMapping("/"+ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin+"/v1/info_send_queue")
public class InfoSendQueueController {

    @Resource
    public IInfoSendQueueService service;

    @Operation(summary = "获取分页结果集")
    @Parameters(  {
            @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/get_page_result")
    public ApiResult< PageResult<InfoSendQueue> > getPageResult(@Valid @RequestBody BasePage vo)   {

        return new  ApiResult(service.getPageResult(vo));

    }

    @Operation(summary = "插入消息")
    @Parameters(  {
            @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insert")
    public ApiResult< String> getPageResult(@Valid @RequestBody InfoSendQueue vo)   {

        return new  ApiResult(service.insert(vo));

    }

}
