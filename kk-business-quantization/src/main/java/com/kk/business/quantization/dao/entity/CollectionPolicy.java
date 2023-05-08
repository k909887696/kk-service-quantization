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
 * 数据策略
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
@TableName("collection_policy")
@ApiModel(value = "数据策略对象", description = "数据策略")
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
    * 调用类编号
    */
    @ApiModelProperty("调用类编号")
    @TableField("invoke_code")
    private String invokeCode;

    /**
     * 调用类方法
     */
    @ApiModelProperty("调用类方法")
    @TableField("invoke_method")
    private String invokeMethod;

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
    * 调用类方法参数
    */
    @ApiModelProperty("调用类方法参数")
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
    private Integer invokeCycleTime;

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getInvokeCode() {
        return invokeCode;
    }

    public void setInvokeCode(String invokeCode) {
        this.invokeCode = invokeCode;
    }
    public String getInvokeCycle() {
        return invokeCycle;
    }

    public void setInvokeCycle(String invokeCycle) {
        this.invokeCycle = invokeCycle;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getPreRunTime() {
        return preRunTime;
    }

    public void setPreRunTime(Date preRunTime) {
        this.preRunTime = preRunTime;
    }
    public String getInvokeParams() {
        return invokeParams;
    }

    public void setInvokeParams(String invokeParams) {
        this.invokeParams = invokeParams;
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
    public Integer getInvokeCycleTime() {
        return invokeCycleTime;
    }

    public void setInvokeCycleTime(Integer invokeCycleTime) {
        this.invokeCycleTime = invokeCycleTime;
    }

    public String getInvokeMethod() {
        return invokeMethod;
    }

    public void setInvokeMethod(String invokeMethod) {
        this.invokeMethod = invokeMethod;
    }


    @Override
    public String toString() {
        return "CollectionPolicy{" +
                "policyId='" + policyId + '\'' +
                ", name='" + name + '\'' +
                ", invokeCode='" + invokeCode + '\'' +
                ", invokeMethod='" + invokeMethod + '\'' +
                ", invokeCycle='" + invokeCycle + '\'' +
                ", createTime=" + createTime +
                ", preRunTime=" + preRunTime +
                ", invokeParams='" + invokeParams + '\'' +
                ", runCount=" + runCount +
                ", exMsg='" + exMsg + '\'' +
                ", invokeCycleTime=" + invokeCycleTime +
                '}';
    }
}
