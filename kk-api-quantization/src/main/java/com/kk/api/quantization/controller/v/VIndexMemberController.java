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
import com.kk.business.quantization.serviceapi.IndexMemberServiceApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.web.model.ApiResult;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import com.kk.business.quantization.model.vobase.req.IndexMemberListReqVo;
import com.kk.business.quantization.model.vobase.res.IndexMemberListResVo;
import com.kk.business.quantization.model.vobase.req.IndexMemberAddReqVo;
import com.kk.business.quantization.model.vobase.req.IndexMemberEditReqVo;
import com.kk.business.quantization.model.vobase.res.IndexMemberResVo;
import com.kk.business.quantization.model.vobase.req.IndexMemberDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.IndexMemberDeleteReqVo;
/**
 * <p>
 * 申万行业明细 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Tag(name = "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/VIndexMember",description = "申万行业明细(接口)")
@RestController
@RequestMapping("/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin + "/v1/VIndexMember")
public class VIndexMemberController {

    @Resource
    public IndexMemberServiceApi indexMemberServiceApi;

    @Operation(summary = "获取申万行业明细分页结果集", description = "获取申万行业明细分页结果集")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getIndexMemberPageList")
    //@SaCheckPermission(value = {"v_menu_select_IndexMember"})
    public ApiResult<PageResult<IndexMemberListResVo>> getIndexMemberPageList(
            @RequestBody IndexMemberListReqVo vo)   {
        return new ApiResult<>(indexMemberServiceApi.selectIndexMemberPageList(vo));
    }
    @Operation(summary = "删除申万行业明细", description = "删除申万行业明细")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/deleteIndexMemberById")
    //@SaCheckPermission(value={"v_btn_delete_IndexMember"})
    public ApiResult<?> deleteIndexMemberById(
            @RequestBody IndexMemberDeleteReqVo vo)   {
        indexMemberServiceApi.deleteIndexMemberById(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "插入申万行业明细", description = "插入申万行业明细")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/insertIndexMember")
    //@SaCheckPermission(value={"v_btn_modify_IndexMember"})
    public ApiResult<?> insertIndexMember(
            @RequestBody IndexMemberAddReqVo vo)   {
        indexMemberServiceApi.insertIndexMember(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "更新申万行业明细", description = "更新申万行业明细")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/updateIndexMember")
    //@SaCheckPermission(value={"v_btn_modify_IndexMember"})
    public ApiResult<?> updateIndexMember(
            @RequestBody IndexMemberEditReqVo vo)   {
        indexMemberServiceApi.updateIndexMember(vo);
        return new ApiResult<>();
    }

    @Operation(summary = "查询申万行业明细详情", description = "查询申万行业明细详情")
    @Parameters(  {
        @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
        @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getIndexMemberDetails")
    //@SaCheckPermission(value={"inc_menu_select_IndexMember"})
    public ApiResult< IndexMemberResVo > getIndexMemberDetails(
            @RequestBody IndexMemberDetailsReqVo vo)   {
        return new  ApiResult<>(indexMemberServiceApi.selectIndexMemberById(vo));
    }



}
