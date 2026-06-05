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
 * 系统设置-调度类型
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@TableName("invoke_type")
@Schema(description = "系统设置-调度类型")
public class InvokeType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 調用類型編號(beanId)
    */
    @Schema(description = "調用類型編號(beanId)")
    @TableId("invoke_code")
    private String invokeCode;

    /**
    * 调用类型名称
    */
    @Schema(description = "调用类型名称")
    @TableField("name")
    private String name;

    /**
    * 调用类型对象
    */
    @Schema(description = "调用类型对象")
    @TableField("invoke_object")
    private String invokeObject;

    /**
    * 调用类型方法
    */
    @Schema(description = "调用类型方法")
    @TableField("invoke_method")
    private String invokeMethod;

    /**
    * 创建时间
    */
    @Schema(description = "创建时间")
    @TableField("create_time")
    private Date createTime;

    /**
    * 调用类型参数
    */
    @Schema(description = "调用类型参数")
    @TableField("invoke_params")
    private String invokeParams;


}
