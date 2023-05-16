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
 * 概念资金流向新增实体
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
@Data
@ApiModel(value = "概念资金流向新增实体", description = "概念资金流向")
public class ConceptMoneyFlowAddVo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String code;
    private String tradeDate;
    private Integer buySmVol;
    private Double buySmAmount;
    private Integer sellSmVol;
    private Double sellSmAmount;
    private Integer buyMdVol;
    private Double buyMdAmount;
    private Integer sellMdVol;
    private Double sellMdAmount;
    private Integer buyLgVol;
    private Double buyLgAmount;
    private Integer sellLgVol;
    private Double sellLgAmount;
    private Integer buyElgVol;
    private Double buyElgAmount;
    private Integer sellElgVol;
    private Double sellElgAmount;
    private Integer netMfVol;
    private Double netMfAmount;
    private String name;


}
