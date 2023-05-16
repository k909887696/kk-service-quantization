package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 概念资金流向删除实体
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
@Data
@ApiModel(value = "概念资金流向删除实体", description = "概念资金流向")
public class ConceptMoneyFlowDeleteVo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String code;
    private String tradeDate;


}
