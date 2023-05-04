package com.kk.business.quantization.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 数据任务历史表
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
@TableName("collection_task_history")
@ApiModel(value = "数据任务历史表对象", description = "数据任务历史表")
public class CollectionTaskHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 任务编号
    */
    @ApiModelProperty("任务编号")
    @TableId("task_id")
    private String taskId;

    /**
    * 任务名称
    */
    @ApiModelProperty("任务名称")
    @TableField("name")
    private String name;

    /**
    * 所属策略编号
    */
    @ApiModelProperty("所属策略编号")
    @TableField("policy_id")
    private String policyId;

    /**
    * 调用类编号
    */
    @ApiModelProperty("调用类编号")
    @TableField("invoke_code")
    private String invokeCode;

    /**
    * 调用类方法名称
    */
    @ApiModelProperty("调用类方法名称")
    @TableField("invoke_method")
    private String invokeMethod;

    /**
    * 调用类方法参数
    */
    @ApiModelProperty("调用类方法参数")
    @TableField("invoke_params")
    private String invokeParams;

    /**
    * 预执行时间
    */
    @ApiModelProperty("预执行时间")
    @TableField("pre_run_time")
    private Date preRunTime;

    /**
    * 执行时间
    */
    @ApiModelProperty("执行时间")
    @TableField("run_time")
    private Date runTime;

    /**
    * 完成时间
    */
    @ApiModelProperty("完成时间")
    @TableField("finish_time")
    private Date finishTime;

    /**
    * 创建人编号
    */
    @ApiModelProperty("创建人编号")
    @TableField("create_id")
    private String createId;

    /**
    * 创建人名称
    */
    @ApiModelProperty("创建人名称")
    @TableField("create_name")
    private String createName;

    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;

    /**
    * 执行次数
    */
    @ApiModelProperty("执行次数")
    @TableField("run_count")
    private Integer runCount;

    /**
    * 异常信息
    */
    @ApiModelProperty("异常信息")
    @TableField("ex_msg")
    private String exMsg;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }
    public String getInvokeCode() {
        return invokeCode;
    }

    public void setInvokeCode(String invokeCode) {
        this.invokeCode = invokeCode;
    }
    public String getInvokeMethod() {
        return invokeMethod;
    }

    public void setInvokeMethod(String invokeMethod) {
        this.invokeMethod = invokeMethod;
    }
    public String getInvokeParams() {
        return invokeParams;
    }

    public void setInvokeParams(String invokeParams) {
        this.invokeParams = invokeParams;
    }
    public Date getPreRunTime() {
        return preRunTime;
    }

    public void setPreRunTime(Date preRunTime) {
        this.preRunTime = preRunTime;
    }
    public Date getRunTime() {
        return runTime;
    }

    public void setRunTime(Date runTime) {
        this.runTime = runTime;
    }
    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }
    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Integer getRunCount() {
        return runCount;
    }

    public void setRunCount(Integer runCount) {
        this.runCount = runCount;
    }
    public String getExMsg() {
        return exMsg;
    }

    public void setExMsg(String exMsg) {
        this.exMsg = exMsg;
    }

    @Override
    public String toString() {
        return "CollectionTaskHistory{" +
            "taskId=" + taskId +
            ", name=" + name +
            ", policyId=" + policyId +
            ", invokeCode=" + invokeCode +
            ", invokeMethod=" + invokeMethod +
            ", invokeParams=" + invokeParams +
            ", preRunTime=" + preRunTime +
            ", runTime=" + runTime +
            ", finishTime=" + finishTime +
            ", createId=" + createId +
            ", createName=" + createName +
            ", createTime=" + createTime +
            ", runCount=" + runCount +
            ", exMsg=" + exMsg +
        "}";
    }
}
