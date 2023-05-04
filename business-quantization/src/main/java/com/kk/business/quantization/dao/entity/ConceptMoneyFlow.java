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
 * 概念资金流向
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
@TableName("concept_money_flow")
@ApiModel(value = "概念资金流向对象", description = "概念资金流向")
public class ConceptMoneyFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 概念代码
    */
    @ApiModelProperty("概念代码")
    @MppMultiId("code")
    private String code;

    /**
    * 交易日期
    */
    @ApiModelProperty("交易日期")
    @MppMultiId("trade_date")
    private String tradeDate;

    /**
    * 小单买入量（手）
    */
    @ApiModelProperty("小单买入量（手）")
    @TableField("buy_sm_vol")
    private Integer buySmVol;

    /**
    * 小单买入金额（万元）
    */
    @ApiModelProperty("小单买入金额（万元）")
    @TableField("buy_sm_amount")
    private Double buySmAmount;

    /**
    * 小单卖出量（手）
    */
    @ApiModelProperty("小单卖出量（手）")
    @TableField("sell_sm_vol")
    private Integer sellSmVol;

    /**
    * 小单卖出金额（万元）
    */
    @ApiModelProperty("小单卖出金额（万元）")
    @TableField("sell_sm_amount")
    private Double sellSmAmount;

    /**
    * 中单买入量（手）
    */
    @ApiModelProperty("中单买入量（手）")
    @TableField("buy_md_vol")
    private Integer buyMdVol;

    /**
    * 中单买入金额（万元）
    */
    @ApiModelProperty("中单买入金额（万元）")
    @TableField("buy_md_amount")
    private Double buyMdAmount;

    /**
    * 中单卖出量（手）
    */
    @ApiModelProperty("中单卖出量（手）")
    @TableField("sell_md_vol")
    private Integer sellMdVol;

    /**
    * 中单卖出金额（万元）
    */
    @ApiModelProperty("中单卖出金额（万元）")
    @TableField("sell_md_amount")
    private Double sellMdAmount;

    /**
    * 大单买入量（手）
    */
    @ApiModelProperty("大单买入量（手）")
    @TableField("buy_lg_vol")
    private Integer buyLgVol;

    /**
    * 大单买入金额（万元）
    */
    @ApiModelProperty("大单买入金额（万元）")
    @TableField("buy_lg_amount")
    private Double buyLgAmount;

    /**
    * 大单卖出量（手）
    */
    @ApiModelProperty("大单卖出量（手）")
    @TableField("sell_lg_vol")
    private Integer sellLgVol;

    /**
    * 大单卖出金额（万元）
    */
    @ApiModelProperty("大单卖出金额（万元）")
    @TableField("sell_lg_amount")
    private Double sellLgAmount;

    /**
    * 特大单买入量（手）
    */
    @ApiModelProperty("特大单买入量（手）")
    @TableField("buy_elg_vol")
    private Integer buyElgVol;

    /**
    * 特大单买入金额（万元）
    */
    @ApiModelProperty("特大单买入金额（万元）")
    @TableField("buy_elg_amount")
    private Double buyElgAmount;

    /**
    * 特大单卖出量（手）
    */
    @ApiModelProperty("特大单卖出量（手）")
    @TableField("sell_elg_vol")
    private Integer sellElgVol;

    /**
    * 特大单卖出金额（万元）
    */
    @ApiModelProperty("特大单卖出金额（万元）")
    @TableField("sell_elg_amount")
    private Double sellElgAmount;

    /**
    * 净流入量（手）
    */
    @ApiModelProperty("净流入量（手）")
    @TableField("net_mf_vol")
    private Integer netMfVol;

    /**
    * 净流入额（万元）
    */
    @ApiModelProperty("净流入额（万元）")
    @TableField("net_mf_amount")
    private Double netMfAmount;

    /**
    * 概念名称
    */
    @ApiModelProperty("概念名称")
    @TableField("name")
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }
    public Integer getBuySmVol() {
        return buySmVol;
    }

    public void setBuySmVol(Integer buySmVol) {
        this.buySmVol = buySmVol;
    }
    public Double getBuySmAmount() {
        return buySmAmount;
    }

    public void setBuySmAmount(Double buySmAmount) {
        this.buySmAmount = buySmAmount;
    }
    public Integer getSellSmVol() {
        return sellSmVol;
    }

    public void setSellSmVol(Integer sellSmVol) {
        this.sellSmVol = sellSmVol;
    }
    public Double getSellSmAmount() {
        return sellSmAmount;
    }

    public void setSellSmAmount(Double sellSmAmount) {
        this.sellSmAmount = sellSmAmount;
    }
    public Integer getBuyMdVol() {
        return buyMdVol;
    }

    public void setBuyMdVol(Integer buyMdVol) {
        this.buyMdVol = buyMdVol;
    }
    public Double getBuyMdAmount() {
        return buyMdAmount;
    }

    public void setBuyMdAmount(Double buyMdAmount) {
        this.buyMdAmount = buyMdAmount;
    }
    public Integer getSellMdVol() {
        return sellMdVol;
    }

    public void setSellMdVol(Integer sellMdVol) {
        this.sellMdVol = sellMdVol;
    }
    public Double getSellMdAmount() {
        return sellMdAmount;
    }

    public void setSellMdAmount(Double sellMdAmount) {
        this.sellMdAmount = sellMdAmount;
    }
    public Integer getBuyLgVol() {
        return buyLgVol;
    }

    public void setBuyLgVol(Integer buyLgVol) {
        this.buyLgVol = buyLgVol;
    }
    public Double getBuyLgAmount() {
        return buyLgAmount;
    }

    public void setBuyLgAmount(Double buyLgAmount) {
        this.buyLgAmount = buyLgAmount;
    }
    public Integer getSellLgVol() {
        return sellLgVol;
    }

    public void setSellLgVol(Integer sellLgVol) {
        this.sellLgVol = sellLgVol;
    }
    public Double getSellLgAmount() {
        return sellLgAmount;
    }

    public void setSellLgAmount(Double sellLgAmount) {
        this.sellLgAmount = sellLgAmount;
    }
    public Integer getBuyElgVol() {
        return buyElgVol;
    }

    public void setBuyElgVol(Integer buyElgVol) {
        this.buyElgVol = buyElgVol;
    }
    public Double getBuyElgAmount() {
        return buyElgAmount;
    }

    public void setBuyElgAmount(Double buyElgAmount) {
        this.buyElgAmount = buyElgAmount;
    }
    public Integer getSellElgVol() {
        return sellElgVol;
    }

    public void setSellElgVol(Integer sellElgVol) {
        this.sellElgVol = sellElgVol;
    }
    public Double getSellElgAmount() {
        return sellElgAmount;
    }

    public void setSellElgAmount(Double sellElgAmount) {
        this.sellElgAmount = sellElgAmount;
    }
    public Integer getNetMfVol() {
        return netMfVol;
    }

    public void setNetMfVol(Integer netMfVol) {
        this.netMfVol = netMfVol;
    }
    public Double getNetMfAmount() {
        return netMfAmount;
    }

    public void setNetMfAmount(Double netMfAmount) {
        this.netMfAmount = netMfAmount;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ConceptMoneyFlow{" +
            "code=" + code +
            ", tradeDate=" + tradeDate +
            ", buySmVol=" + buySmVol +
            ", buySmAmount=" + buySmAmount +
            ", sellSmVol=" + sellSmVol +
            ", sellSmAmount=" + sellSmAmount +
            ", buyMdVol=" + buyMdVol +
            ", buyMdAmount=" + buyMdAmount +
            ", sellMdVol=" + sellMdVol +
            ", sellMdAmount=" + sellMdAmount +
            ", buyLgVol=" + buyLgVol +
            ", buyLgAmount=" + buyLgAmount +
            ", sellLgVol=" + sellLgVol +
            ", sellLgAmount=" + sellLgAmount +
            ", buyElgVol=" + buyElgVol +
            ", buyElgAmount=" + buyElgAmount +
            ", sellElgVol=" + sellElgVol +
            ", sellElgAmount=" + sellElgAmount +
            ", netMfVol=" + netMfVol +
            ", netMfAmount=" + netMfAmount +
            ", name=" + name +
        "}";
    }
}
