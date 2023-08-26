package com.kk.business.quantization.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
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
    private Double startClose;
    /**
     * 收盘价
     */
    @ApiModelProperty("收盘价")
    private Double endClose;
    /**
     * 区间涨跌幅
     */
    @ApiModelProperty("区间涨跌幅")
    private Double rangePct;
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

}
