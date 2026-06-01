package com.kk.business.quantization.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

/**
 * <p>
 * 个股日线行情
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
@TableName("daily")
@Schema(name = "个股日线行情对象", description = "个股日线行情")
public class Daily implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 股票代码
    */
    @Schema(description = "股票代码")
    @MppMultiId("ts_code")
    private String tsCode;

    /**
    * 交易日期
    */
    @Schema(description = "交易日期")
    @MppMultiId("trade_date")
    private String tradeDate;

    /**
    * 开盘价
    */
    @Schema(description = "开盘价")
    @TableField("`open`")
    private Double open;

    /**
    * 最高价
    */
    @Schema(description = "最高价")
    @TableField("high")
    private Double high;

    /**
    * 最低价
    */
    @Schema(description = "最低价")
    @TableField("low")
    private Double low;

    /**
    * 收盘价
    */
    @Schema(description = "收盘价")
    @TableField("`close`")
    private Double close;

    /**
    * 上一日收盘价
    */
    @Schema(description = "上一日收盘价")
    @TableField("pre_close")
    private Double preClose;

    /**
    * 涨跌额
    */
    @Schema(description = "涨跌额")
    @TableField("`change`")
    private Double change;

    /**
    * 涨跌幅%
    */
    @Schema(description = "涨跌幅%")
    @TableField("pct_chg")
    private Double pctChg;

    /**
    * 成交量 （手）
    */
    @Schema(description = "成交量 （手）")
    @TableField("vol")
    private Double vol;

    /**
    * 成交额 （千元）
    */
    @Schema(description = "成交额 （千元）")
    @TableField("amount")
    private Double amount;

    public String getTsCode() {
        return tsCode;
    }

    public void setTsCode(String tsCode) {
        this.tsCode = tsCode;
    }
    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }
    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }
    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }
    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }
    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }
    public Double getPreClose() {
        return preClose;
    }

    public void setPreClose(Double preClose) {
        this.preClose = preClose;
    }
    public Double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
    }
    public Double getPctChg() {
        return pctChg;
    }

    public void setPctChg(Double pctChg) {
        this.pctChg = pctChg;
    }
    public Double getVol() {
        return vol;
    }

    public void setVol(Double vol) {
        this.vol = vol;
    }
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Daily{" +
            "tsCode=" + tsCode +
            ", tradeDate=" + tradeDate +
            ", open=" + open +
            ", high=" + high +
            ", low=" + low +
            ", close=" + close +
            ", preClose=" + preClose +
            ", change=" + change +
            ", pctChg=" + pctChg +
            ", vol=" + vol +
            ", amount=" + amount +
        "}";
    }
}
