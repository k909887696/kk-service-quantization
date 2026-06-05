package com.kk.business.quantization.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
 import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
/**
 * <p>
 * 概念日线行情
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@TableName("concept_daily")
@Schema(description = "概念日线行情")
public class ConceptDaily implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 概念编号
    */
    @Schema(description = "概念编号")
    @MppMultiId("concept_id")
    private String conceptId;

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
    @TableField("open")
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
    @TableField("close")
    private Double close;

    /**
    * 前收盘价
    */
    @Schema(description = "前收盘价")
    @TableField("pre_close")
    private Double preClose;

    /**
    * 涨跌额
    */
    @Schema(description = "涨跌额")
    @TableField("change")
    private Double change;

    /**
    * 涨跌幅%
    */
    @Schema(description = "涨跌幅%")
    @TableField("pct_chg")
    private Double pctChg;

    /**
    * 成交量
    */
    @Schema(description = "成交量")
    @TableField("vol")
    private Double vol;

    /**
    * 成交额
    */
    @Schema(description = "成交额")
    @TableField("amount")
    private Double amount;


}
