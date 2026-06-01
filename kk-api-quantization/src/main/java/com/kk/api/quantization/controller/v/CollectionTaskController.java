package com.kk.api.quantization.controller.v;


import com.kk.business.quantization.dao.entity.CollectionTask;
import com.kk.business.quantization.model.vo.ExecuteTaskVo;
import com.kk.business.quantization.model.vo.RetryExecuteTaskVo;
import com.kk.business.quantization.model.vo.SearchTaskListVo;
import com.kk.business.quantization.service.ICollectionTaskService;
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
 * 数据任务 前端控制器
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
 @Tag(name = "/"+ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin+"/v1/collection_task",description = "系统设置-数据任务")
@ResponseBody
@RestController
@RequestMapping("/"+ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin+"/v1/collection_task")
public class CollectionTaskController {

    @Resource
    public ICollectionTaskService service;

    @Operation(summary = "获取分页结果集")
    @Parameters(  {
            @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/get_collection_task_page_result")
    public ApiResult< PageResult<CollectionTask> > getCollectionTaskPageResult(@Valid @RequestBody SearchTaskListVo vo)   {

        return new  ApiResult(service.getCollectionTaskPageResult(vo));

    }


    @Operation(summary = "重新执行任务")
    @Parameters(  {
            @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/retry_execute_task")
    public ApiResult< String > retryExecuteTask(@Valid @RequestBody RetryExecuteTaskVo vo)   {
        service.retryExecuteTask(vo.getTaskId());
        return  ApiResult.getSuccessResult("请求成功");

    }


}
