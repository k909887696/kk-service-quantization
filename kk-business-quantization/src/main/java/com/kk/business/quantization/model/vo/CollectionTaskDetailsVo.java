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
 * 系统设置-数据任务查询详情实体
 * </p>
 *
 * @author kk
 * @since 2023-08-24
 */
@Data
@ApiModel(value = "系统设置-数据任务查询详情实体", description = "系统设置-数据任务")
public class CollectionTaskDetailsVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 任务编号
    */
    @ApiModelProperty("任务编号")
    private String taskId;


}
