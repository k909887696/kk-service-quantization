package com.kk.api.quantization.controller.v;


import com.kk.business.quantization.dao.entity.CollectionPolicy;
import com.kk.business.quantization.model.vo.ExcutePolicyByHandVo;
import com.kk.business.quantization.model.vo.ExecutePolicyVo;
import com.kk.business.quantization.model.vo.SearchPolicyListVo;
import com.kk.business.quantization.model.vo.SearchPolicyVo;
import com.kk.business.quantization.service.ICollectionPolicyService;
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
 * 数据策略 前端控制器
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
 @Tag(name = "/"+ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin+"/v1/collection_policy",description = "系统设置-数据策略")
@ResponseBody
@RestController
@RequestMapping("/"+ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin+"/v1/collection_policy")
public class CollectionPolicyController {

    @Resource
    public ICollectionPolicyService service;



    @Operation(summary = "新增策略")
    @Parameters(  {
            @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insert_policy")
    public ApiResult< String > insertPolicy(@Valid @RequestBody CollectionPolicy vo)   {

        return new  ApiResult(service.insert(vo));

    }

    @Operation(summary = "修改策略")
    @Parameters(  {
            @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/update_policy")
    public ApiResult< String > updatePolicy(@Valid @RequestBody CollectionPolicy vo)   {

        return new  ApiResult(service.update(vo));

    }

    @Operation(summary = "根据策略编号获取单个策略")
    @Parameters(  {
            @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false ),
            @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/get_policy")
    public ApiResult< CollectionPolicy > getPolicy(@Valid @RequestBody SearchPolicyVo vo)   {

        return new  ApiResult(service.getPolicy(vo));

    }

    @Operation(summary = "获取分页结果集")
    @Parameters(  {
            @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/get_collection_policy_page_result")
    public ApiResult< PageResult<CollectionPolicy> > getCollectionPolicyPageResult(@Valid @RequestBody SearchPolicyListVo vo)   {

        return new  ApiResult(service.getCollectionPolicyPageResult(vo));

    }

    @Operation(summary = "手动执行策略")
    @Parameters (  {
            @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/execute_policy_by_hand")
    public ApiResult< CollectionPolicy > executePolicyByHand(@Valid @RequestBody ExcutePolicyByHandVo vo)   {
        service.executePolicyByHand(vo.getPolicyId());
        return ApiResult.getSuccessResult("请求成功！");

    }


}
