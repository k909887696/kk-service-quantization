package com.kk.api.quantization.controller;


import com.kk.business.quantization.dao.entity.CollectionTask;
import com.kk.business.quantization.model.vo.ExecuteTaskVo;
import com.kk.business.quantization.model.vo.SearchTaskListVo;
import com.kk.business.quantization.schedule.TaskExecutorByHand;
import com.kk.business.quantization.service.ICollectionTaskService;
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
 * 数据任务 前端控制器
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
 @Api(value = "/quantization/api/v1/collection_task",tags = "系统设置-数据任务")
@ResponseBody
@RestController
@RequestMapping("/quantization/api/v1/collection_task")
public class CollectionTaskController {

    @Resource
    public ICollectionTaskService service;
    @Resource
    public TaskExecutorByHand taskExecutorByHand;
    @ApiOperation("获取分页结果集")
    @ApiImplicitParams(  {
    @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header",  dataType = "String")
    })
    @PostMapping("/get_collection_task_page_result")
    public ApiResult< PageResult<CollectionTask> > getCollectionTaskPageResult(@Valid @RequestBody SearchTaskListVo vo)   {

        return new  ApiResult(service.getCollectionTaskPageResult(vo));

    }

    @ApiOperation("手动执行一次任务")
    @ApiImplicitParams(  {
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header",  dataType = "String")
    })
    @PostMapping("/execute_task")
    public ApiResult< String > executeTask(@Valid @RequestBody ExecuteTaskVo vo)   {
        taskExecutorByHand.taskSchedule();
        return new  ApiResult("");

    }

}
