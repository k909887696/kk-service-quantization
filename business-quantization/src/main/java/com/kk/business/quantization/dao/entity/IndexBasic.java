package com.kk.business.quantization.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 指数基本信息
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
@TableName("index_basic")
@ApiModel(value = "指数基本信息对象", description = "指数基本信息")
public class IndexBasic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * TS指数代码
    */
    @ApiModelProperty("TS指数代码")
    @TableId("ts_code")
    private String tsCode;

    /**
    * 简称
    */
    @ApiModelProperty("简称")
    @TableField("name")
    private String name;

    /**
    * 指数全称
    */
    @ApiModelProperty("指数全称")
    @TableField("fullname")
    private String fullname;

    /**
    * 市场
    */
    @ApiModelProperty("市场")
    @TableField("market")
    private String market;

    /**
    * 发布方
    */
    @ApiModelProperty("发布方")
    @TableField("publisher")
    private String publisher;

    /**
    * 指数风格
    */
    @ApiModelProperty("指数风格")
    @TableField("index_type")
    private String indexType;

    /**
    * 指数类别
    */
    @ApiModelProperty("指数类别")
    @TableField("category")
    private String category;

    /**
    * 基期
    */
    @ApiModelProperty("基期")
    @TableField("base_date")
    private String baseDate;

    /**
    * 基点
    */
    @ApiModelProperty("基点")
    @TableField("base_point")
    private Double basePoint;

    /**
    * 发布日期
    */
    @ApiModelProperty("发布日期")
    @TableField("list_date")
    private String listDate;

    /**
    * 加权方式
    */
    @ApiModelProperty("加权方式")
    @TableField("weight_rule")
    private String weightRule;

    /**
    * 描述
    */
    @ApiModelProperty("描述")
    @TableField("`desc`")
    private String desc;

    /**
    * 终止日期
    */
    @ApiModelProperty("终止日期")
    @TableField("exp_date")
    private String expDate;

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
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public String getBaseDate() {
        return baseDate;
    }

    public void setBaseDate(String baseDate) {
        this.baseDate = baseDate;
    }
    public Double getBasePoint() {
        return basePoint;
    }

    public void setBasePoint(Double basePoint) {
        this.basePoint = basePoint;
    }
    public String getListDate() {
        return listDate;
    }

    public void setListDate(String listDate) {
        this.listDate = listDate;
    }
    public String getWeightRule() {
        return weightRule;
    }

    public void setWeightRule(String weightRule) {
        this.weightRule = weightRule;
    }
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    @Override
    public String toString() {
        return "IndexBasic{" +
            "tsCode=" + tsCode +
            ", name=" + name +
            ", fullname=" + fullname +
            ", market=" + market +
            ", publisher=" + publisher +
            ", indexType=" + indexType +
            ", category=" + category +
            ", baseDate=" + baseDate +
            ", basePoint=" + basePoint +
            ", listDate=" + listDate +
            ", weightRule=" + weightRule +
            ", desc=" + desc +
            ", expDate=" + expDate +
        "}";
    }
}
