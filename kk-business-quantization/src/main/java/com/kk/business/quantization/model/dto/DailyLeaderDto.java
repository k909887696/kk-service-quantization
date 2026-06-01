package com.kk.business.quantization.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DailyLeaderDto {

    /**
     * 股票代码
     */
    @Schema(description = "代码")
    private String tsCode;

    /**
     * 名称
     */
    @Schema(description = "名称")
    private String name;
    /**
     * 最高价
     */
    @Schema(description = "最高价")
    private Double high;
    /**
     * 最低价
     */
    @Schema(description = "最低价")
    private Double low;
    /**
     * 开盘价
     */
    @Schema(description = "开盘价")
    private Double startClose;
    /**
     * 收盘价
     */
    @Schema(description = "收盘价")
    private Double endClose;
    /**
     * 区间涨跌幅
     */
    @Schema(description = "区间涨跌幅")
    private Double rangePct;
    /**
     * 区间回撤涨跌幅
     */
    @Schema(description = "区间回撤涨跌幅")
    private Double rollBackPct;
    /**
     * 区间最大涨跌幅
     */
    @Schema(description = "区间最大涨跌幅")
    private Double maxPct;

}
