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
 * DAG主表
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Data
@TableName("sy_dag_main")
@Schema(description = "DAG主表")
public class SyDagMain implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * DAG编码
    */
    @Schema(description = "DAG编码")
    @TableId("dag_id")
    private String dagId;

    /**
    * DAG名称
    */
    @Schema(description = "DAG名称")
    @TableField("dag_name")
    private String dagName;

    /**
    * 关联开始DAG节点编号
    */
    @Schema(description = "关联开始DAG节点编号")
    @TableField("node_code")
    private String nodeCode;

    /**
    * 执行节点默认参数
    */
    @Schema(description = "执行节点默认参数")
    @TableField("run_params")
    private String runParams;

    /**
    * 执行cron表达式
    */
    @Schema(description = "执行cron表达式")
    @TableField("run_cron")
    private String runCron;

    /**
    * 预执行时间
    */
    @Schema(description = "预执行时间")
    @TableField("pre_run_time")
    private Date preRunTime;

    /**
    * 状态（1：启用，0：禁用）
    */
    @Schema(description = "状态（1：启用，0：禁用）")
    @TableField("status")
    private String status;

    /**
    * 创建时间
    */
    @Schema(description = "创建时间")
    @TableField("create_time")
    private Date createTime;

    /**
    * 异常信息
    */
    @Schema(description = "异常信息")
    @TableField("ex_message")
    private String exMessage;

    /**
    * 画布设计信息
    */
    @Schema(description = "画布设计信息")
    @TableField("dag_flow_info")
    private String dagFlowInfo;


}
