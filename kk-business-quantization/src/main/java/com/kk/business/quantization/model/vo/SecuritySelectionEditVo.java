package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 个人自选股编辑实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@ApiModel(value = "个人自选股编辑实体", description = "个人自选股")
public class SecuritySelectionEditVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tsCode;
    private String tradeDate;
    private String selectType;


}
