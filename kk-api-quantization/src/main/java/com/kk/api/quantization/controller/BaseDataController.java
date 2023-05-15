package com.kk.api.quantization.controller;

import com.kk.business.quantization.dao.entity.Daily;
import com.kk.business.quantization.model.dto.BaseDataItemMapGetDto;
import com.kk.business.quantization.model.po.tushare.DailyVo;
import com.kk.business.quantization.model.vo.BaseDataItemMapGetVo;
import com.kk.business.quantization.service.IBaseDataService;
import com.kk.business.quantization.service.IDailyService;
import com.kk.business.quantization.service.executor.ITaskExecutor;
import com.kk.common.base.email.EmailSendMsg;
import com.kk.common.base.email.EmailUtil;
import com.kk.common.utils.JsonUtil;
import com.kk.common.web.model.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author: kk
 * @Date: 2021/11/15 16:23
 */
@Api(value = "/quantization/api/v1/basedata",tags = "基础数据")
@ResponseBody
@RestController
@RequestMapping("/quantization/api/v1/basedata")
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

    @ApiOperation("查询基础数据字典")
    @ApiImplicitParams(  {
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header",  dataType = "String")
    })
    @PostMapping("/get_base_data_item_map")
    public ApiResult<BaseDataItemMapGetDto> getBaseDataItemMap(@Valid @RequestBody BaseDataItemMapGetVo vo)   {

        return new  ApiResult(baseDataService.getBaseDataItemMap(vo));

    }


    @ApiOperation("daily")
    @ApiImplicitParams(  {
            // @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = true, dataType = "String"),
            // @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = true, dataType = "String"),
            // @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = true, dataType = "String"),
            // @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = true, dataType = "String"),
            @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header",  dataType = "String")
    })
    @PostMapping("/daily")
    public ApiResult<List<Daily>> daily(@Valid @RequestBody DailyVo vo) throws Exception {
        log.info("daily===================");
        dailyTaskExecutor.executeTask(JsonUtil.getJSONString(vo));
        return new  ApiResult();

    }

    @ApiOperation("sendEmailMsg")
    @ApiImplicitParams(  {
            // @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = true, dataType = "String"),
            // @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = true, dataType = "String"),
            // @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = true, dataType = "String"),
            // @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = true, dataType = "String"),
            @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header",  dataType = "String")
    })
    @PostMapping("/sendEmailMsg")
    public ApiResult<List<Daily>> daily(@Valid @RequestBody EmailSendMsg vo) throws Exception {
        log.info("daily===================");
        emailUtil.sendMimeMail(vo);
        return new  ApiResult();

    }

    @ApiOperation("daily1")
    @ApiImplicitParams(  {
            // @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = true, dataType = "String"),
            // @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = true, dataType = "String"),
            // @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = true, dataType = "String"),
            // @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = true, dataType = "String"),
            @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header",  dataType = "String")
    })
    @PostMapping("/daily1")
    public ApiResult<List<Daily>> daily1(@Valid @RequestBody DailyVo vo) throws Exception {
        log.info("daily1===================");
        dailyTaskExecutor.executeTask(JsonUtil.getJSONString(vo));
        return new  ApiResult();

    }
}
