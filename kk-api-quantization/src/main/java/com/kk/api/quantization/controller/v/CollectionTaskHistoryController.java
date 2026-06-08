package com.kk.api.quantization.controller.v;


import com.kk.business.quantization.model.vobase.req.CollectionTaskHistoryListReqVo;
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
import com.kk.business.quantization.service.ICollectionTaskHistoryService;
import com.kk.common.web.model.ApiResult;
import com.kk.common.base.model.PageResult;
import com.kk.common.base.model.BasePage;
import com.kk.business.quantization.dao.entity.CollectionTaskHistory;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kk
 * @since 2022-10-17
 */
@Tag(name = "/"+ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin+"/v1/collection_task_history",description = "系统设置-数据任务-历史")
@ResponseBody
@RestController
@RequestMapping("/"+ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin+"/v1/collection_task_history")
public class CollectionTaskHistoryController {

    @Resource
    public ICollectionTaskHistoryService service;

    @Operation(summary = "获取分页结果集")
    @Parameters(  {
            @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/get_collection_task_history_page_result")
    public ApiResult< PageResult<CollectionTaskHistory> > getCollectionTaskHistoryPageResult(@Valid @RequestBody CollectionTaskHistoryListReqVo vo)   {

        return new  ApiResult(service.selectCollectionTaskHistoryPageList(vo));

    }

}
