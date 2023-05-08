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
 * 任务执行器
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
@TableName("invoke_type")
@ApiModel(value = "任务执行器对象", description = "任务执行器")
public class InvokeType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 执行器编号（注入bean id）
    */
    @ApiModelProperty("执行器编号（注入bean id）")
    @TableId("invoke_code")
    private String invokeCode;

    /**
    * 执行器名称
    */
    @ApiModelProperty("执行器名称")
    @TableField("name")
    private String name;

    /**
    * 执行器对象
    */
    @ApiModelProperty("执行器对象")
    @TableField("invoke_object")
    private String invokeObject;

    /**
    * 执行器方法
    */
    @ApiModelProperty("执行器方法")
    @TableField("invoke_method")
    private String invokeMethod;

    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;

    /**
    * 调用参数（示例）
    */
    @ApiModelProperty("调用参数（示例）")
    @TableField("invoke_params")
    private String invokeParams;

    public String getInvokeCode() {
        return invokeCode;
    }

    public void setInvokeCode(String invokeCode) {
        this.invokeCode = invokeCode;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getInvokeObject() {
        return invokeObject;
    }

    public void setInvokeObject(String invokeObject) {
        this.invokeObject = invokeObject;
    }
    public String getInvokeMethod() {
        return invokeMethod;
    }

    public void setInvokeMethod(String invokeMethod) {
        this.invokeMethod = invokeMethod;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getInvokeParams() {
        return invokeParams;
    }

    public void setInvokeParams(String invokeParams) {
        this.invokeParams = invokeParams;
    }

    @Override
    public String toString() {
        return "InvokeType{" +
            "invokeCode=" + invokeCode +
            ", name=" + name +
            ", invokeObject=" + invokeObject +
            ", invokeMethod=" + invokeMethod +
            ", createTime=" + createTime +
            ", invokeParams=" + invokeParams +
        "}";
    }
}
