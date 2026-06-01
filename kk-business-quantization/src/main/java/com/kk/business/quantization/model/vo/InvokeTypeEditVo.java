package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 系统设置-调度类型编辑实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@Schema(name = "系统设置-调度类型编辑实体", description = "系统设置-调度类型")
public class InvokeTypeEditVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 調用類型編號(beanId)
    */
    @Schema(description = "調用類型編號(beanId)")
    private String invokeCode;
    /**
    * 调用类型名称
    */
    @Schema(description = "调用类型名称")
    private String name;
    /**
    * 调用类型对象
    */
    @Schema(description = "调用类型对象")
    private String invokeObject;
    /**
    * 调用类型方法
    */
    @Schema(description = "调用类型方法")
    private String invokeMethod;
    /**
    * 创建时间
    */
    @Schema(description = "创建时间")
    private Date createTime;
    /**
    * 调用类型参数
    */
    @Schema(description = "调用类型参数")
    private String invokeParams;


}
