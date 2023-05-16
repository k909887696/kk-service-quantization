package com.kk.business.quantization.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 个人自选股列表返回实体
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
@Data
@ApiModel(value = "个人自选股列表返回实体", description = "个人自选股")
public class SecuritySelectionListDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tsCode;
    private String tradeDate;
    private String selectType;


}
