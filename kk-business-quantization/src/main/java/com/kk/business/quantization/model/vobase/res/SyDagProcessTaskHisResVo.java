package com.kk.business.quantization.model.vobase.res;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * DAG执行实例历史任务详情返回实体
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Data
@Schema( description = "DAG执行实例历史任务详情返回实体")
public class SyDagProcessTaskHisResVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 任务编号
    */
    @Schema(description = "任务编号")
    private String processTaskId;
    /**
    * 任务名称
    */
    @Schema(description = "任务名称")
    private String processTaskName;
    /**
    * 执行实例编码
    */
    @Schema(description = "执行实例编码")
    private String processId;
    /**
    * DAG主表编码
    */
    @Schema(description = "DAG主表编码")
    private String dagId;
    /**
    * 执行节点编号
    */
    @Schema(description = "执行节点编号")
    private String nodeCode;
    /**
    * 起始执行节点编号
    */
    @Schema(description = "起始执行节点编号")
    private String topNodeCode;
    /**
    * 上级执行实例任务编号
    */
    @Schema(description = "上级执行实例任务编号")
    private String preProcessTaskId;
    /**
    * 下级执行实例任务编号
    */
    @Schema(description = "下级执行实例任务编号")
    private String nextProcessTaskId;
    /**
    * 上级执行节点编号
    */
    @Schema(description = "上级执行节点编号")
    private String preNodeCode;
    /**
    * 下级执行节点编号
    */
    @Schema(description = "下级执行节点编号")
    private String nextNodeCode;
    /**
    * 执行参数
    */
    @Schema(description = "执行参数")
    private String runParam;
    /**
    * 预执行时间
    */
    @Schema(description = "预执行时间")
    private Date preRunTime;
    /**
    * 执行次数（超过20次，就挂起）
    */
    @Schema(description = "执行次数（超过20次，就挂起）")
    private Integer runCount;
    /**
    * 备注
    */
    @Schema(description = "备注")
    private String reamrk;
    /**
    * 创建时间
    */
    @Schema(description = "创建时间")
    private Date createTime;
    /**
    * 创建人编号
    */
    @Schema(description = "创建人编号")
    private String createId;
    /**
    * 创建人名称
    */
    @Schema(description = "创建人名称")
    private String createName;
    /**
    * 完成时间
    */
    @Schema(description = "完成时间")
    private Date finishTime;
    /**
    * 异常信息
    */
    @Schema(description = "异常信息")
    private String exMessage;


}
