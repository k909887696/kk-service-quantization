package com.kk.api.quantization.controller.v;


import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.Resource;
import com.kk.business.quantization.serviceapi.KdjCrossServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.KdjCrossListReqVo;
import com.kk.business.quantization.model.vobase.res.KdjCrossListResVo;
import com.kk.business.quantization.model.vobase.req.KdjCrossAddReqVo;
import com.kk.business.quantization.model.vobase.req.KdjCrossEditReqVo;
import com.kk.business.quantization.model.vobase.res.KdjCrossResVo;
import com.kk.business.quantization.model.vobase.req.KdjCrossDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.KdjCrossDeleteReqVo;
/**
 * <p>
 * kdj交叉点 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/VKdjCross",description = "kdj交叉点(接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/VKdjCross")
public class VKdjCrossController {

    @Resource
    public KdjCrossServiceApi kdjCrossServiceApi;

    @Operation(summary = "获取kdj交叉点分页结果集", description = "获取kdj交叉点分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getKdjCrossPageList")
    //@SaCheckPermission(value = {"v_menu_select_KdjCross"})
    public ApiResult<PageResult<KdjCrossListResVo>> getKdjCrossPageList(
            @RequestBody KdjCrossListReqVo vo)   {
        return new ApiResult<>(kdjCrossServiceApi.selectKdjCrossPageList(vo));
    }
    @Operation(summary = "删除kdj交叉点", description = "删除kdj交叉点")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteKdjCrossById")
    //@SaCheckPermission(value={"v_btn_delete_KdjCross"})
    public ApiResult<?> deleteKdjCrossById(
            @RequestBody KdjCrossDeleteReqVo vo)   {
        kdjCrossServiceApi.deleteKdjCrossById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入kdj交叉点", description = "插入kdj交叉点")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertKdjCross")
    //@SaCheckPermission(value={"v_btn_modify_KdjCross"})
    public ApiResult<?> insertKdjCross(
            @RequestBody KdjCrossAddReqVo vo)   {
        kdjCrossServiceApi.insertKdjCross(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新kdj交叉点", description = "更新kdj交叉点")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateKdjCross")
    //@SaCheckPermission(value={"v_btn_modify_KdjCross"})
    public ApiResult<?> updateKdjCross(
            @RequestBody KdjCrossEditReqVo vo)   {
        kdjCrossServiceApi.updateKdjCross(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询kdj交叉点详情", description = "查询kdj交叉点详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getKdjCrossDetails")
    //@SaCheckPermission(value={"inc_menu_select_KdjCross"})
    public ApiResult< KdjCrossResVo > getKdjCrossDetails(
            @RequestBody KdjCrossDetailsReqVo vo)   {
        return new  ApiResult<>(kdjCrossServiceApi.selectKdjCrossById(vo));
    }



}
