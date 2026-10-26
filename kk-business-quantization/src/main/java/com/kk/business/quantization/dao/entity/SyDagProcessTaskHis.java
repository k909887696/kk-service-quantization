package com.kk.business.quantization.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
 import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * DAG执行实例历史任务
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Data
@TableName("sy_dag_process_task_his")
@Schema(description = "DAG执行实例历史任务")
public class SyDagProcessTaskHis implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 任务编号
    */
    @Schema(description = "任务编号")
    @TableId("process_task_id")
    private String processTaskId;

    /**
    * 任务名称
    */
    @Schema(description = "任务名称")
    @TableField("process_task_name")
    private String processTaskName;

    /**
    * 执行实例编码
    */
    @Schema(description = "执行实例编码")
    @TableField("process_id")
    private String processId;

    /**
    * DAG主表编码
    */
    @Schema(description = "DAG主表编码")
    @TableField("dag_id")
    private String dagId;

    /**
    * 执行节点编号
    */
    @Schema(description = "执行节点编号")
    @TableField("node_code")
    private String nodeCode;

    /**
    * 起始执行节点编号
    */
    @Schema(description = "起始执行节点编号")
    @TableField("top_node_code")
    private String topNodeCode;

    /**
    * 上级执行实例任务编号
    */
    @Schema(description = "上级执行实例任务编号")
    @TableField("pre_process_task_id")
    private String preProcessTaskId;

    /**
    * 下级执行实例任务编号
    */
    @Schema(description = "下级执行实例任务编号")
    @TableField("next_process_task_id")
    private String nextProcessTaskId;

    /**
    * 上级执行节点编号
    */
    @Schema(description = "上级执行节点编号")
    @TableField("pre_node_code")
    private String preNodeCode;

    /**
    * 下级执行节点编号
    */
    @Schema(description = "下级执行节点编号")
    @TableField("next_node_code")
    private String nextNodeCode;

    /**
    * 执行参数
    */
    @Schema(description = "执行参数")
    @TableField("run_param")
    private String runParam;

    /**
    * 预执行时间
    */
    @Schema(description = "预执行时间")
    @TableField("pre_run_time")
    private Date preRunTime;

    /**
    * 执行次数（超过20次，就挂起）
    */
    @Schema(description = "执行次数（超过20次，就挂起）")
    @TableField("run_count")
    private Integer runCount;

    /**
    * 备注
    */
    @Schema(description = "备注")
    @TableField("reamrk")
    private String reamrk;

    /**
    * 创建时间
    */
    @Schema(description = "创建时间")
    @TableField("create_time")
    private Date createTime;

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
    * 完成时间
    */
    @Schema(description = "完成时间")
    @TableField("finish_time")
    private Date finishTime;

    /**
    * 异常信息
    */
    @Schema(description = "异常信息")
    @TableField("ex_message")
    private String exMessage;


}
