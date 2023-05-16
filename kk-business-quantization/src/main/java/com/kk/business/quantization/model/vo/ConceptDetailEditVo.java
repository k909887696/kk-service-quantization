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
 * 概念明细编辑实体
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
@Data
@ApiModel(value = "概念明细编辑实体", description = "概念明细")
public class ConceptDetailEditVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 概念编号
    */
    @ApiModelProperty("概念编号")
    private String conceptId;
    /**
    * 概念名称
    */
    @ApiModelProperty("概念名称")
    private String conceptName;
    /**
    * 股票编号
    */
    @ApiModelProperty("股票编号")
    private String tsCode;
    /**
    * 股票名称
    */
    @ApiModelProperty("股票名称")
    private String name;
    /**
    * 加入日期
    */
    @ApiModelProperty("加入日期")
    private String inDate;
    /**
    * 剔除日期
    */
    @ApiModelProperty("剔除日期")
    private String outDate;
    /**
    * 股票代码
    */
    @ApiModelProperty("股票代码")
    private String symbol;
    /**
    * 权重
    */
    @ApiModelProperty("权重")
    private String weight;


}
