package com.kk.api.quantization.controller.v;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.SaTokenInfo;
import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.business.quantization.dao.entity.Daily;
import com.kk.business.quantization.model.dto.BaseDataItemMapGetDto;
import com.kk.business.quantization.model.po.tushare.DailyVo;
import com.kk.business.quantization.model.vo.BaseDataItemMapGetVo;
import com.kk.business.quantization.service.IBaseDataService;
import com.kk.business.quantization.service.IDailyService;
import com.kk.business.quantization.service.executor.ITaskExecutor;
import com.kk.common.auth.LoginInfo;
import com.kk.common.auth.LoginUserInfo;
import com.kk.common.auth.LoginUserJurInfo;
import com.kk.common.auth.LoginUtil;
import com.kk.common.base.email.EmailSendMsg;
import com.kk.common.base.email.EmailUtil;
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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

import java.util.Date;
import java.util.List;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;

/**
 * @Author: kk
 * @Date: 2021/11/15 16:23
 */
@Tag(name=  "/"+ ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin+"/v1/basedata", description = "基础数据")
@ResponseBody
@RestController
@RequestMapping("/"+ServiceSystemKey.ServiceName+"/"+SystemKey.ApiPrefixLogin+"/v1/basedata")
@Slf4j
public class BaseDataController {

    @Resource(name="DailyTaskExecutor")
    public ITaskExecutor dailyTaskExecutor;
    @Resource
    public IBaseDataService baseDataService;
    @Resource
    public EmailUtil emailUtil;
    @Resource
    public IDailyService dailyService;

    @Operation(summary = "查询基础数据字典")
    @Parameters(  {
            @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false),
            @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })

    @PostMapping("/get_base_data_item_map")
    public ApiResult<BaseDataItemMapGetDto> getBaseDataItemMap(@Valid @RequestBody BaseDataItemMapGetVo vo)   {
        List<String> permissionList = StpUtil.getPermissionList();
        return new  ApiResult(baseDataService.getBaseDataItemMap(vo));

    }


    @Operation(summary = "test-jenkins-deploy")
    @Parameters(  {
            // @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = true, dataType = "String"),
            // @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = true, dataType = "String"),
            // @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = true, dataType = "String"),
            // @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = true, dataType = "String"),
            @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/daily")
    public ApiResult<List<Daily>> daily(@Valid @RequestBody DailyVo vo) throws Exception {
        dailyTaskExecutor.executeTask(JsonUtil.getJSONString(vo));
        return new  ApiResult();

    }

    @Operation(summary = "sendEmailMsg")
    @Parameters(  {
            @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = true),
            // @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = true, dataType = "String"),
            // @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = true, dataType = "String"),
            // @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = true, dataType = "String"),
            @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @SaCheckPermission(value = {"user:export", "data:export"}, mode = SaMode.OR)
    @PostMapping("/sendEmailMsg")
    public ApiResult<List<Daily>> daily(@Valid @RequestBody EmailSendMsg vo) throws Exception {
        List<String> permissionList = StpUtil.getPermissionList();
        emailUtil.sendMimeMail(vo);
        return new  ApiResult();

    }

    @Operation(summary = "登录sa-token jwt模式")
    @Parameters(  {
            @Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = true),
            // @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = true, dataType = "String"),
            // @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = true, dataType = "String"),
            // @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = true, dataType = "String"),
            @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/login")
    public ApiResult<LoginUserInfo> daily1(@Valid @RequestBody LoginVo vo) throws Exception {
        log.info("daily1===================");
        StpUtil.login(vo.getUserId());
        LoginInfo loginInfo = new LoginInfo();
        LoginUserInfo userInfo = new LoginUserInfo();
        userInfo.setUserId(vo.getUserId()); // Set user ID
        userInfo.setUserName(vo.getUserId());
        userInfo.setUserType("user");
        userInfo.setLoginTime(new Date());
        userInfo.setLoginChannel("web");
        loginInfo.setLoginUserInfo(userInfo);
        loginInfo.setLoginUserJurInfo(new LoginUserJurInfo());
        loginInfo = LoginUtil.login(loginInfo);
        return new  ApiResult<>(loginInfo.getLoginUserInfo());

    }
}
