package com.kk.business.quantization.model.dto;

import io.swagger.annotations.ApiModelProperty;

public class DailyLeaderDto {

    /**
     * 股票代码
     */
    @ApiModelProperty("代码")
    private String tsCode;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;
    /**
     * 最高价
     */
    @ApiModelProperty("最高价")
    private Double high;
    /**
     * 最低价
     */
    @ApiModelProperty("最低价")
    private Double low;
    /**
     * 开盘价
     */
    @ApiModelProperty("开盘价")
    private Double sdClose;
    /**
     * 收盘价
     */
    @ApiModelProperty("收盘价")
    private Double edClose;
    /**
     * 区间涨跌幅
     */
    @ApiModelProperty("区间涨跌幅")
    private Double sePct;
    /**
     * 区间回撤涨跌幅
     */
    @ApiModelProperty("区间回撤涨跌幅")
    private Double rollBackPct;
    /**
     * 区间最大涨跌幅
     */
    @ApiModelProperty("区间最大涨跌幅")
    private Double maxPct;

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

    public Double getSdClose() {
        return sdClose;
    }

    public void setSdClose(Double sdClose) {
        this.sdClose = sdClose;
    }

    public Double getEdClose() {
        return edClose;
    }

    public void setEdClose(Double edClose) {
        this.edClose = edClose;
    }

    public Double getSePct() {
        return sePct;
    }

    public void setSePct(Double sePct) {
        this.sePct = sePct;
    }

    public Double getRollBackPct() {
        return rollBackPct;
    }

    public void setRollBackPct(Double rollBackPct) {
        this.rollBackPct = rollBackPct;
    }

    public Double getMaxPct() {
        return maxPct;
    }

    public void setMaxPct(Double maxPct) {
        this.maxPct = maxPct;
    }
}
