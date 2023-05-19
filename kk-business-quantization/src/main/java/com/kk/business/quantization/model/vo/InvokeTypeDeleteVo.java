package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 系统设置-调度类型删除实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@ApiModel(value = "系统设置-调度类型删除实体", description = "系统设置-调度类型")
public class InvokeTypeDeleteVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 調用類型編號(beanId)
    */
    @ApiModelProperty("調用類型編號(beanId)")
    private String invokeCode;


}
