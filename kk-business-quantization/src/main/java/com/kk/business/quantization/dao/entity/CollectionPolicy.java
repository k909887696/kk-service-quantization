package com.kk.business.quantization.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * <p>
 * 系统设置-数据策略
 * </p>
 *
 * @author kk
 * @since 2023-08-24
 */
@Data
@TableName("collection_policy")
@ApiModel(value = "系统设置-数据策略对象", description = "系统设置-数据策略")
public class CollectionPolicy implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 策略编号
    */
    @ApiModelProperty("策略编号")
    @TableId("policy_id")
    private String policyId;

    /**
    * 策略名称
    */
    @ApiModelProperty("策略名称")
    @TableField("name")
    private String name;

    /**
    * 调度类型编号
    */
    @ApiModelProperty("调度类型编号")
    @TableField("invoke_method")
    private String invokeMethod;

    /**
    * 调度类型方法
    */
    @ApiModelProperty("调度类型方法")
    @TableField("invoke_code")
    private String invokeCode;

    /**
    * 执行周期（min：每几分钟，hour：每几小时，day:每天，week：每周，month：每月，year：每年）
    */
    @ApiModelProperty("执行周期（min：每几分钟，hour：每几小时，day:每天，week：每周，month：每月，year：每年）")
    @TableField("invoke_cycle")
    private String invokeCycle;

    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;

    /**
    * 预执行时间
    */
    @ApiModelProperty("预执行时间")
    @TableField("pre_run_time")
    private Date preRunTime;

    /**
    * 调度类型参数
    */
    @ApiModelProperty("调度类型参数")
    @TableField("invoke_params")
    private String invokeParams;

    /**
    * 执行次数（超过10次自动挂起）
    */
    @ApiModelProperty("执行次数（超过10次自动挂起）")
    @TableField("run_count")
    private Integer runCount;

    /**
    * 异常信息
    */
    @ApiModelProperty("异常信息")
    @TableField("ex_msg")
    private String exMsg;

    /**
    * 执行周期大小
    */
    @ApiModelProperty("执行周期大小")
    @TableField("invoke_cycle_time")
    private String invokeCycleTime;

    /**
    * 调度渠道（公共渠道：1，其余值为自定义调度作业单独执行）
    */
    @ApiModelProperty("调度渠道（公共渠道：1，其余值为自定义调度作业单独执行）")
    @TableField("channel")
    private String channel;


}
