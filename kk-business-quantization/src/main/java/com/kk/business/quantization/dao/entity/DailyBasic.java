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
 * 个股每日指标
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
@TableName("daily_basic")
@ApiModel(value = "个股每日指标对象", description = "个股每日指标")
public class DailyBasic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * ts股票代码
    */
    @ApiModelProperty("ts股票代码")
    @MppMultiId("ts_code")
    private String tsCode;

    /**
    * 交易日期
    */
    @ApiModelProperty("交易日期")
    @MppMultiId("trade_date")
    private String tradeDate;

    /**
    * 当日收盘价
    */
    @ApiModelProperty("当日收盘价")
    @TableField("close")
    private Double close;

    /**
    * 换手率（%）
    */
    @ApiModelProperty("换手率（%）")
    @TableField("turnover_rate")
    private Double turnoverRate;

    /**
    * 换手率（自由流通股）
    */
    @ApiModelProperty("换手率（自由流通股）")
    @TableField("turnover_rate_f")
    private Double turnoverRateF;

    /**
    * 量比
    */
    @ApiModelProperty("量比")
    @TableField("volume_ratio")
    private Double volumeRatio;

    /**
    * 市盈率（总市值/净利润， 亏损的PE为空）
    */
    @ApiModelProperty("市盈率（总市值/净利润， 亏损的PE为空）")
    @TableField("pe")
    private Double pe;

    /**
    * 市盈率（TTM，亏损的PE为空）
    */
    @ApiModelProperty("市盈率（TTM，亏损的PE为空）")
    @TableField("pe_ttm")
    private Double peTtm;

    /**
    * 市净率（总市值/净资产）
    */
    @ApiModelProperty("市净率（总市值/净资产）")
    @TableField("pb")
    private Double pb;

    /**
    * 市销率
    */
    @ApiModelProperty("市销率")
    @TableField("ps")
    private Double ps;

    /**
    * 市销率（TTM）
    */
    @ApiModelProperty("市销率（TTM）")
    @TableField("ps_ttm")
    private Double psTtm;

    /**
    * 股息率 （%）
    */
    @ApiModelProperty("股息率 （%）")
    @TableField("dv_ratio")
    private Double dvRatio;

    /**
    * 股息率（TTM）（%）
    */
    @ApiModelProperty("股息率（TTM）（%）")
    @TableField("dv_ttm")
    private Double dvTtm;

    /**
    * 总股本 （万股）
    */
    @ApiModelProperty("总股本 （万股）")
    @TableField("total_share")
    private Double totalShare;

    /**
    * 流通股本 （万股）
    */
    @ApiModelProperty("流通股本 （万股）")
    @TableField("float_share")
    private Double floatShare;

    /**
    * 自由流通股本 （万）
    */
    @ApiModelProperty("自由流通股本 （万）")
    @TableField("free_share")
    private Double freeShare;

    /**
    * 总市值 （万元）
    */
    @ApiModelProperty("总市值 （万元）")
    @TableField("total_mv")
    private Double totalMv;

    /**
    * 流通市值（万元）
    */
    @ApiModelProperty("流通市值（万元）")
    @TableField("circ_mv")
    private Double circMv;

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
    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }
    public Double getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(Double turnoverRate) {
        this.turnoverRate = turnoverRate;
    }
    public Double getTurnoverRateF() {
        return turnoverRateF;
    }

    public void setTurnoverRateF(Double turnoverRateF) {
        this.turnoverRateF = turnoverRateF;
    }
    public Double getVolumeRatio() {
        return volumeRatio;
    }

    public void setVolumeRatio(Double volumeRatio) {
        this.volumeRatio = volumeRatio;
    }
    public Double getPe() {
        return pe;
    }

    public void setPe(Double pe) {
        this.pe = pe;
    }
    public Double getPeTtm() {
        return peTtm;
    }

    public void setPeTtm(Double peTtm) {
        this.peTtm = peTtm;
    }
    public Double getPb() {
        return pb;
    }

    public void setPb(Double pb) {
        this.pb = pb;
    }
    public Double getPs() {
        return ps;
    }

    public void setPs(Double ps) {
        this.ps = ps;
    }
    public Double getPsTtm() {
        return psTtm;
    }

    public void setPsTtm(Double psTtm) {
        this.psTtm = psTtm;
    }
    public Double getDvRatio() {
        return dvRatio;
    }

    public void setDvRatio(Double dvRatio) {
        this.dvRatio = dvRatio;
    }
    public Double getDvTtm() {
        return dvTtm;
    }

    public void setDvTtm(Double dvTtm) {
        this.dvTtm = dvTtm;
    }
    public Double getTotalShare() {
        return totalShare;
    }

    public void setTotalShare(Double totalShare) {
        this.totalShare = totalShare;
    }
    public Double getFloatShare() {
        return floatShare;
    }

    public void setFloatShare(Double floatShare) {
        this.floatShare = floatShare;
    }
    public Double getFreeShare() {
        return freeShare;
    }

    public void setFreeShare(Double freeShare) {
        this.freeShare = freeShare;
    }
    public Double getTotalMv() {
        return totalMv;
    }

    public void setTotalMv(Double totalMv) {
        this.totalMv = totalMv;
    }
    public Double getCircMv() {
        return circMv;
    }

    public void setCircMv(Double circMv) {
        this.circMv = circMv;
    }

    @Override
    public String toString() {
        return "DailyBasic{" +
            "tsCode=" + tsCode +
            ", tradeDate=" + tradeDate +
            ", close=" + close +
            ", turnoverRate=" + turnoverRate +
            ", turnoverRateF=" + turnoverRateF +
            ", volumeRatio=" + volumeRatio +
            ", pe=" + pe +
            ", peTtm=" + peTtm +
            ", pb=" + pb +
            ", ps=" + ps +
            ", psTtm=" + psTtm +
            ", dvRatio=" + dvRatio +
            ", dvTtm=" + dvTtm +
            ", totalShare=" + totalShare +
            ", floatShare=" + floatShare +
            ", freeShare=" + freeShare +
            ", totalMv=" + totalMv +
            ", circMv=" + circMv +
        "}";
    }
}
