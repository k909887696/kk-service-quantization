package com.kk.api.quantization.controller.nl;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.business.quantization.dao.entity.Daily;
import com.kk.business.quantization.model.dto.BaseDataItemMapGetDto;
import com.kk.business.quantization.model.po.tushare.DailyVo;
import com.kk.business.quantization.model.vo.BaseDataItemMapGetVo;
import com.kk.business.quantization.service.IBaseDataService;
import com.kk.business.quantization.service.IDailyService;
import com.kk.business.quantization.service.executor.ITaskExecutor;
import com.kk.business.quantization.serviceapi.SyUserServiceApi;
import com.kk.common.auth.LoginInfo;
import com.kk.common.auth.LoginUserInfo;
import com.kk.common.auth.LoginUserJurInfo;
import com.kk.common.auth.LoginUtil;
import com.kk.common.base.email.EmailSendMsg;
import com.kk.common.base.email.EmailUtil;
import com.kk.common.base.model.LoginPermissionResVo;
import com.kk.common.base.model.LoginPermissionVo;
import com.kk.common.base.model.LoginVo;
import com.kk.common.constant.SystemKey;
import com.kk.common.utils.JsonUtil;
import com.kk.common.web.model.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author: kk
 * @Date: 2021/11/15 16:23
 */
@Tag(name=  "/"+ ServiceSystemKey.ServiceName +"/"+ SystemKey.ApiPrefixNoLogin +"/v1/login", description = "基础数据")
@ResponseBody
@RestController
@RequestMapping("/"+ServiceSystemKey.ServiceName+"/"+ SystemKey.ApiPrefixNoLogin +"/v1/login")
@Slf4j
public class LoginController {


    @Resource
    public EmailUtil emailUtil;

    @Resource
    public SyUserServiceApi syUserServiceApi;
    @Operation(summary = "sendEmailMsg")
    @Parameters(  {
            @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
            // @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = true, dataType = "String"),
            // @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = true, dataType = "String"),
            // @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = true, dataType = "String"),
            @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    //@SaCheckPermission(value = {"user:export", "data:export"}, mode = SaMode.OR)
    @PostMapping("/sendEmailMsg")
    public ApiResult<List<Daily>> sendEmailMsg(@Valid @RequestBody EmailSendMsg vo) throws Exception {
        List<String> permissionList = StpUtil.getPermissionList();
        emailUtil.sendMimeMail(vo);
        return new  ApiResult();

    }

    @Operation(summary = "登录sa-token jwt模式")
    @Parameters(  {
            @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
            // @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = true, dataType = "String"),
            // @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = true, dataType = "String"),
            // @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = true, dataType = "String"),
            @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/login")
    public ApiResult<LoginUserInfo> login(@Valid @RequestBody LoginVo vo) throws Exception {

        return new  ApiResult<>(syUserServiceApi.loginUser( vo));

    }


    @Operation(summary = "获取登录信息sa-token jwt模式")
    @Parameters(  {
            @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
            // @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = true, dataType = "String"),
            // @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = true, dataType = "String"),
            // @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = true, dataType = "String"),
            @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getLoginInfo")
    public ApiResult<LoginUserInfo> getLoginInfo() throws Exception {

        LoginUserInfo loginInfo = LoginUtil.getLoginUserInfo();
        return new  ApiResult<>(loginInfo);
    }

    @Operation(summary = "登出sa-token jwt模式")
    @Parameters(  {
            @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
             @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/logOut")
    public ApiResult<?> logOut() {

        LoginUtil.logout();
        return new  ApiResult<>();
    }
    @Operation(summary = "权限校验sa-token jwt模式")
    @Parameters(  {
            @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/getPermissionList")
    public ApiResult<LoginPermissionResVo> getPermissionList(@RequestBody LoginPermissionVo vo) {

        return new  ApiResult<>(LoginUtil.validPermission(vo));
    }

}
