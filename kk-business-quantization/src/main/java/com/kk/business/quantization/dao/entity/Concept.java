package com.kk.business.quantization.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 股票概念
 * </p>
 *
 * @author kk
 * @since 2021-12-24
 */
@TableName("concept")
@ApiModel(value = "股票概念对象", description = "股票概念")
public class Concept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 概念代码
    */
    @ApiModelProperty("概念代码")
    @TableId("code")
    private String code;

    /**
    * 概念名称
    */
    @ApiModelProperty("概念名称")
    @TableField("name")
    private String name;

    /**
    * 来源
    */
    @ApiModelProperty("来源 ts:tushare,ths:同花顺 90：东方财富")
    @TableField("src")
    private String src;

    /**
    * 交易所
    */
    @ApiModelProperty("交易所")
    @TableField("exchange")
    private String exchange;

    /**
    * 上市日期
    */
    @ApiModelProperty("上市日期")
    @TableField("list_date")
    private String listDate;

    /**
    * N概念指数S特色指数
    */
    @ApiModelProperty("N概念指数S特色指数")
    @TableField("type")
    private String type;

    /**
    * 成分个数
    */
    @ApiModelProperty("成分个数")
    @TableField("count")
    private Integer count;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
    public String getListDate() {
        return listDate;
    }

    public void setListDate(String listDate) {
        this.listDate = listDate;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Concept{" +
            "code=" + code +
            ", name=" + name +
            ", src=" + src +
            ", exchange=" + exchange +
            ", listDate=" + listDate +
            ", type=" + type +
            ", count=" + count +
        "}";
    }
}
