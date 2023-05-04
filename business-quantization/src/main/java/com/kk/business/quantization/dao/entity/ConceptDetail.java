package com.kk.business.quantization.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 概念明细
 * </p>
 *
 * @author kk
 * @since 2021-12-24
 */
@TableName("concept_detail")
@ApiModel(value = "概念明细对象", description = "概念明细")
public class ConceptDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 概念代码
    */
    @MppMultiId
    @ApiModelProperty("概念代码")
    @TableField("concept_id")
    private String conceptId;

    /**
    * 概念名称
    */
    @ApiModelProperty("概念名称")
    @TableField("concept_name")
    private String conceptName;

    /**
    * ts股票代码
    */
    @MppMultiId
    @ApiModelProperty("ts股票代码")
    @TableField("ts_code")
    private String tsCode;

    /**
    * 股票名称
    */
    @ApiModelProperty("股票名称")
    @TableField("name")
    private String name;

    /**
    * 加入日期
    */
    @ApiModelProperty("加入日期")
    @TableField("in_date")
    private String inDate;

    /**
    * 移除日期
    */
    @ApiModelProperty("移除日期")
    @TableField("out_date")
    private String outDate;

    /**
    * 股票代码
    */
    @ApiModelProperty("股票代码")
    @TableField("symbol")
    private String symbol;

    /**
    * 权重
    */
    @ApiModelProperty("权重")
    @TableField("weight")
    private Double weight;

    public String getConceptId() {
        return conceptId;
    }

    public void setConceptId(String conceptId) {
        this.conceptId = conceptId;
    }
    public String getConceptName() {
        return conceptName;
    }

    public void setConceptName(String conceptName) {
        this.conceptName = conceptName;
    }
    public String getTsCode() {
        return tsCode;
    }

    public void setTsCode(String tsCode) {
        this.tsCode = tsCode;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }
    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "ConceptDetail{" +
            "conceptId=" + conceptId +
            ", conceptName=" + conceptName +
            ", tsCode=" + tsCode +
            ", name=" + name +
            ", inDate=" + inDate +
            ", outDate=" + outDate +
            ", symbol=" + symbol +
            ", weight=" + weight +
        "}";
    }
}
