package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.kk.common.base.model.BasePage;
/**
 * <p>
 * 概念明细列表查询实体
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
@Data
@ApiModel(value = "概念明细列表查询实体", description = "概念明细")
public class ConceptDetailListVo extends BasePage{

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
    * 加入日期开始
    */
    @ApiModelProperty("加入日期开始")
    private String inDateStart;
    /**
    * 加入日期结束
    */
    @ApiModelProperty("加入日期结束")
    private String inDateEnd;

    /**
    * 剔除日期开始
    */
    @ApiModelProperty("剔除日期开始")
    private String outDateStart;
    /**
    * 剔除日期结束
    */
    @ApiModelProperty("剔除日期结束")
    private String outDateEnd;

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
