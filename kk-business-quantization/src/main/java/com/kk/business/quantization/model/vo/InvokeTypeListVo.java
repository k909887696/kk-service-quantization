package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.kk.common.base.model.BasePage;
/**
 * <p>
 * 系统设置-调度类型	列表查询实体
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
@Data
@ApiModel(value = "系统设置-调度类型	列表查询实体", description = "系统设置-调度类型	")
public class InvokeTypeListVo extends BasePage{

    private static final long serialVersionUID = 1L;

    /**
    * 調用類型編號(beanId)
    */
    @ApiModelProperty("調用類型編號(beanId)")
    private String invokeCode;
    /**
    * 调用类型名称
    */
    @ApiModelProperty("调用类型名称")
    private String name;
    /**
    * 调用类型对象
    */
    @ApiModelProperty("调用类型对象")
    private String invokeObject;
    /**
    * 调用类型方法
    */
    @ApiModelProperty("调用类型方法")
    private String invokeMethod;
    /**
    * 创建时间开始
    */
    @ApiModelProperty("创建时间开始")
    private Date createTimeStart;
    /**
    * 创建时间结束
    */
    @ApiModelProperty("创建时间结束")
    private Date createTimeEnd;

    /**
    * 调用类型参数
    */
    @ApiModelProperty("调用类型参数")
    private String invokeParams;


}
