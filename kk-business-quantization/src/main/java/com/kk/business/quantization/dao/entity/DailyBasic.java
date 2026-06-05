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
 * 个股每日指标
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@TableName("daily_basic")
@Schema(description = "个股每日指标")
public class DailyBasic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * TS代码
    */
    @Schema(description = "TS代码")
    @MppMultiId("ts_code")
    private String tsCode;

    /**
    * 交易日期
    */
    @Schema(description = "交易日期")
    @MppMultiId("trade_date")
    private String tradeDate;

    /**
    * 当日收盘价
    */
    @Schema(description = "当日收盘价")
    @TableField("close")
    private Double close;

    /**
    * 换手率（%）
    */
    @Schema(description = "换手率（%）")
    @TableField("turnover_rate")
    private Double turnoverRate;

    /**
    * 换手率（自由流通股）
    */
    @Schema(description = "换手率（自由流通股）")
    @TableField("turnover_rate_f")
    private Double turnoverRateF;

    /**
    * 量比
    */
    @Schema(description = "量比")
    @TableField("volume_ratio")
    private Double volumeRatio;

    /**
    * 市盈率（总市值/净利润， 亏损的PE为空）
    */
    @Schema(description = "市盈率（总市值/净利润， 亏损的PE为空）")
    @TableField("pe")
    private Double pe;

    /**
    * 市盈率（TTM，亏损的PE为空）
    */
    @Schema(description = "市盈率（TTM，亏损的PE为空）")
    @TableField("pe_ttm")
    private Double peTtm;

    /**
    * 市净率（总市值/净资产）
    */
    @Schema(description = "市净率（总市值/净资产）")
    @TableField("pb")
    private Double pb;

    /**
    * 市销率
    */
    @Schema(description = "市销率")
    @TableField("ps")
    private Double ps;

    /**
    * 市销率（TTM）
    */
    @Schema(description = "市销率（TTM）")
    @TableField("ps_ttm")
    private Double psTtm;

    /**
    * 股息率 （%）
    */
    @Schema(description = "股息率 （%）")
    @TableField("dv_ratio")
    private Double dvRatio;

    /**
    * 股息率（TTM）（%）
    */
    @Schema(description = "股息率（TTM）（%）")
    @TableField("dv_ttm")
    private Double dvTtm;

    /**
    * 总股本 （万股）
    */
    @Schema(description = "总股本 （万股）")
    @TableField("total_share")
    private Double totalShare;

    /**
    * 流通股本 （万股）
    */
    @Schema(description = "流通股本 （万股）")
    @TableField("float_share")
    private Double floatShare;

    /**
    * 自由流通股本 （万）
    */
    @Schema(description = "自由流通股本 （万）")
    @TableField("free_share")
    private Double freeShare;

    /**
    * 当日总市值（元）
    */
    @Schema(description = "当日总市值（元）")
    @TableField("total_mv")
    private Double totalMv;

    /**
    * 流通市值（万元）
    */
    @Schema(description = "流通市值（万元）")
    @TableField("circ_mv")
    private Double circMv;


}
