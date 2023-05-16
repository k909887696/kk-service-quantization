package com.kk.api.quantization.controller;


import com.kk.business.quantization.dao.entity.CollectionPolicy;
import com.kk.business.quantization.model.vo.ExecutePolicyVo;
import com.kk.business.quantization.model.vo.SearchPolicyListVo;
import com.kk.business.quantization.model.vo.SearchPolicyVo;
import com.kk.business.quantization.service.ICollectionPolicyService;
import com.kk.business.quantization.service.schedule.TaskExecutorSchedule;
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
 * 数据策略 前端控制器
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
 @Api(value = "/quantization/api/v1/collection_policy",tags = "系统设置-数据策略")
@ResponseBody
@RestController
@RequestMapping("/quantization/api/v1/collection_policy")
public class CollectionPolicyController {

    @Resource
    public ICollectionPolicyService service;
    @Resource
    public TaskExecutorSchedule taskExecutorSchedule;


    @ApiOperation("新增策略")
    @ApiImplicitParams(  {
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header",  dataType = "String")
    })
    @PostMapping("/insert_policy")
    public ApiResult< String > insertPolicy(@Valid @RequestBody CollectionPolicy vo)   {

        return new  ApiResult(service.insert(vo));

    }

    @ApiOperation("修改策略")
    @ApiImplicitParams(  {
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header",  dataType = "String")
    })
    @PostMapping("/update_policy")
    public ApiResult< String > updatePolicy(@Valid @RequestBody CollectionPolicy vo)   {

        return new  ApiResult(service.update(vo));

    }

    @ApiOperation("根据策略编号获取单个策略")
    @ApiImplicitParams(  {
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header",  dataType = "String")
    })
    @PostMapping("/get_policy")
    public ApiResult< CollectionPolicy > getPolicy(@Valid @RequestBody SearchPolicyVo vo)   {

        return new  ApiResult(service.getPolicy(vo));

    }

    @ApiOperation("获取分页结果集")
    @ApiImplicitParams(  {
    @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"),
    @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header",  dataType = "String")
    })
    @PostMapping("/get_collection_policy_page_result")
    public ApiResult< PageResult<CollectionPolicy> > getCollectionPolicyPageResult(@Valid @RequestBody SearchPolicyListVo vo)   {

        return new  ApiResult(service.getCollectionPolicyPageResult(vo));

    }

    @ApiOperation("手动执行一次策略")
    @ApiImplicitParams(  {
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header",  dataType = "String")
    })
    @PostMapping("/execute_policy")
    public ApiResult< String > executePolicy(@Valid @RequestBody ExecutePolicyVo vo)   {

        return new  ApiResult(taskExecutorSchedule.policyScheduleByHand(vo.getPolicyIds()));

    }

}
