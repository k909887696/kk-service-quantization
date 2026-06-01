package com.kk.business.quantization.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统设置-数据任务
 * </p>
 *
 * @author kk
 * @since 2023-08-24
 */
@Data
@TableName("collection_task")
@Schema(name = "系统设置-数据任务对象", description = "系统设置-数据任务")
public class CollectionTask implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 任务编号
    */
    @Schema(description = "任务编号")
    @TableId("task_id")
    private String taskId;

    /**
    * 任务名称
    */
    @Schema(description = "任务名称")
    @TableField("name")
    private String name;

    /**
    * 所属策略编号
    */
    @Schema(description = "所属策略编号")
    @TableField("policy_id")
    private String policyId;

    /**
    * 调用类编号
    */
    @Schema(description = "调用类编号")
    @TableField("invoke_code")
    private String invokeCode;

    /**
    * 调用类方法名称
    */
    @Schema(description = "调用类方法名称")
    @TableField("invoke_method")
    private String invokeMethod;

    /**
    * 调用类方法参数
    */
    @Schema(description = "调用类方法参数")
    @TableField("invoke_params")
    private String invokeParams;

    /**
    * 预执行时间
    */
    @Schema(description = "预执行时间")
    @TableField("pre_run_time")
    private Date preRunTime;

    /**
    * 执行时间
    */
    @Schema(description = "执行时间")
    @TableField("run_time")
    private Date runTime;

    /**
    * 完成时间
    */
    @Schema(description = "完成时间")
    @TableField("finish_time")
    private Date finishTime;

    /**
    * 创建人编号
    */
    @Schema(description = "创建人编号")
    @TableField("create_id")
    private String createId;

    /**
    * 创建人名称
    */
    @Schema(description = "创建人名称")
    @TableField("create_name")
    private String createName;

    /**
    * 创建时间
    */
    @Schema(description = "创建时间")
    @TableField("create_time")
    private Date createTime;

    /**
    * 执行次数
    */
    @Schema(description = "执行次数")
    @TableField("run_count")
    private Integer runCount;

    /**
    * 异常信息
    */
    @Schema(description = "异常信息")
    @TableField("ex_msg")
    private String exMsg;

    /**
    * 调度渠道（公共渠道：1，其余值为自定义调度作业单独执行）
    */
    @Schema(description = "调度渠道（公共渠道：1，其余值为自定义调度作业单独执行）")
    @TableField("channel")
    private String channel;


}
