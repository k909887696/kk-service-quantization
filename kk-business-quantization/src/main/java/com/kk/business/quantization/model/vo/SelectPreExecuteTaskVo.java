package com.kk.business.quantization.model.vo;

import com.kk.common.base.model.BasePage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author kk
 * @since 2023/8/26
 */
@Data
public class SelectPreExecuteTaskVo extends BasePage {

    private static final long serialVersionUID = 1L;


    /**
     * 预执行时间结束
     */
    @ApiModelProperty("预执行时间结束")
    private Date preRunTimeEnd;


    /**
     * 执行次数（超过10次自动挂起）
     */
    @ApiModelProperty("执行次数（超过10次自动挂起）")
    private Integer runCount;
    /**
     * 调度渠道（公共渠道：空值 null，其余值为自定义调度作业单独执行）
     */
    @ApiModelProperty("调度渠道（公共渠道：空值 null，其余值为自定义调度作业单独执行）")
    private String channel;

}
