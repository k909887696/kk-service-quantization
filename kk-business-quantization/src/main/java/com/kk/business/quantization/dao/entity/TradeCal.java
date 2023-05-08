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
 * 交易日历
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
@TableName("trade_cal")
@ApiModel(value = "交易日历对象", description = "交易日历")
public class TradeCal implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 交易所 SSE上交所 SZSE深交所
    */
    @ApiModelProperty("交易所 SSE上交所 SZSE深交所")
    @MppMultiId("exchange")
    private String exchange;

    /**
    * 日历日期
    */
    @ApiModelProperty("日历日期")
    @MppMultiId("cal_date")
    private String calDate;

    /**
    * 是否交易 0休市 1交易
    */
    @ApiModelProperty("是否交易 0休市 1交易")
    @TableField("is_open")
    private String isOpen;

    /**
    * 上一个交易日
    */
    @ApiModelProperty("上一个交易日")
    @TableField("pretrade_date")
    private String pretradeDate;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
    public String getCalDate() {
        return calDate;
    }

    public void setCalDate(String calDate) {
        this.calDate = calDate;
    }
    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }
    public String getPretradeDate() {
        return pretradeDate;
    }

    public void setPretradeDate(String pretradeDate) {
        this.pretradeDate = pretradeDate;
    }

    @Override
    public String toString() {
        return "TradeCal{" +
            "exchange=" + exchange +
            ", calDate=" + calDate +
            ", isOpen=" + isOpen +
            ", pretradeDate=" + pretradeDate +
        "}";
    }
}
