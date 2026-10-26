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
 * DAG执行节点
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Data
@TableName("sy_dag_node")
@Schema(description = "DAG执行节点")
public class SyDagNode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 节点编码
    */
    @Schema(description = "节点编码")
    @TableId("node_code")
    private String nodeCode;

    /**
    * 节点名称
    */
    @Schema(description = "节点名称")
    @TableField("node_name")
    private String nodeName;

    /**
    * 执行类ID
    */
    @Schema(description = "执行类ID")
    @TableField("bean_id")
    private String beanId;

    /**
    * 执行方法名称（为空，默认执行dagRun方法）
    */
    @Schema(description = "执行方法名称（为空，默认执行dagRun方法）")
    @TableField("method_name")
    private String methodName;

    /**
    * 执行参数
    */
    @Schema(description = "执行参数")
    @TableField("run_param")
    private String runParam;

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


}
