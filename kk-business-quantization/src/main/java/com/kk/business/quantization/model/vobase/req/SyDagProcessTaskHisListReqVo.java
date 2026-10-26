package com.kk.business.quantization.model.vobase.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.kk.common.base.model.BasePage;
/**
 * <p>
 * DAG执行实例历史任务列表查询实体
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Data
@Schema(description = "DAG执行实例历史任务列表查询实体")
public class SyDagProcessTaskHisListReqVo extends BasePage{

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
    * 预执行时间开始
    */
    @Schema(description ="预执行时间开始")
    private Date preRunTimeStart;
    /**
    * 预执行时间结束
    */
    @Schema(description ="预执行时间结束")
    private Date preRunTimeEnd;

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
    * 创建时间开始
    */
    @Schema(description ="创建时间开始")
    private Date createTimeStart;
    /**
    * 创建时间结束
    */
    @Schema(description ="创建时间结束")
    private Date createTimeEnd;

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
    * 完成时间开始
    */
    @Schema(description ="完成时间开始")
    private Date finishTimeStart;
    /**
    * 完成时间结束
    */
    @Schema(description ="完成时间结束")
    private Date finishTimeEnd;

    /**
    * 异常信息
    */
    @Schema(description = "异常信息")
    private String exMessage;


}
