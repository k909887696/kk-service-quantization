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
 * DAG执行实例主表
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Data
@TableName("sy_dag_process_main")
@Schema(description = "DAG执行实例主表")
public class SyDagProcessMain implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 执行实例编码
    */
    @Schema(description = "执行实例编码")
    @TableId("process_id")
    private String processId;

    /**
    * 执行实例名称
    */
    @Schema(description = "执行实例名称")
    @TableField("process_name")
    private String processName;

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
