package com.kk.api.quantization.controller.v;


import com.kk.business.quantization.dao.entity.InfoSendQueueHistory;
import com.kk.business.quantization.service.IInfoSendQueueHistoryService;
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
 * 信息发送队列历史表 前端控制器
 * </p>
 *
 * @author kk
 * @since 2021-12-29
 */
 @Tag(name = "/"+ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin+"/v1/info_send_queue_history",description = "信息发送队列历史表")
@ResponseBody
@RestController
@RequestMapping("/"+ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin+"/v1/info_send_queue_history")
public class InfoSendQueueHistoryController {

    @Resource
    public IInfoSendQueueHistoryService service;

    @Operation(summary = "获取分页结果集")
    @Parameters(  {
            @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/get_page_result")
    public ApiResult< PageResult<InfoSendQueueHistory> > getPageResult(@Valid @RequestBody BasePage vo)   {

        return new  ApiResult(service.getPageResult(vo));

    }

}
