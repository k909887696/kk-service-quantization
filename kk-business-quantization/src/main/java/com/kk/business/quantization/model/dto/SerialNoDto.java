package com.kk.business.quantization.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 自定义主键序号详情返回实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@ApiModel(value = "自定义主键序号详情返回实体", description = "自定义主键序号")
public class SerialNoDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private String serialName;
    private Integer nextValue;
    private Integer min;
    private String max;


}
