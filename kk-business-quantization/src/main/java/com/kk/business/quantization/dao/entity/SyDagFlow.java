package com.kk.business.quantization.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
 import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * DAG执行流
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Data
@TableName("sy_dag_flow")
@Schema(description = "DAG执行流")
public class SyDagFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 节点编码
    */
    @Schema(description = "节点编码")
    @TableField("node_code")
    private String nodeCode;

    /**
    * 下级节点编码
    */
    @Schema(description = "下级节点编码")
    @TableField("next_node_code")
    private String nextNodeCode;

    /**
    * 上级节点编码
    */
    @Schema(description = "上级节点编码")
    @TableField("pre_node_code")
    private String preNodeCode;

    /**
    * DAG主表ID
    */
    @Schema(description = "DAG主表ID")
    @TableField("dag_id")
    private String dagId;

    /**
    * DAG流程明细ID
    */
    @Schema(description = "DAG流程明细ID")
    @TableId("dag_flow_id")
    private String dagFlowId;

    /**
    * 画布信息JSON
    */
    @Schema(description = "画布信息JSON")
    @TableField("flow_info")
    private String flowInfo;


}
