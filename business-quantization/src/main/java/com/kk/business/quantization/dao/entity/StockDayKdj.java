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
 * kdj数据
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
@TableName("stock_day_kdj")
@ApiModel(value = "kdj数据对象", description = "kdj数据")
public class StockDayKdj implements Serializable {

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
    * k值
    */
    @ApiModelProperty("k值")
    @TableField("k_value")
    private Double kValue;

    /**
    * d值
    */
    @ApiModelProperty("d值")
    @TableField("d_value")
    private Double dValue;

    /**
    * j值
    */
    @ApiModelProperty("j值")
    @TableField("j_value")
    private Double jValue;

    /**
    * rsv值
    */
    @ApiModelProperty("rsv值")
    @TableField("rsv")
    private Double rsv;

    /**
    * kdj类型（9_3_3）
    */
    @ApiModelProperty("kdj类型（9_3_3）")
    @TableField("kdj_type")
    private String kdjType;

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
    public Double getKValue() {
        return kValue;
    }

    public void setKValue(Double kValue) {
        this.kValue = kValue;
    }
    public Double getDValue() {
        return dValue;
    }

    public void setDValue(Double dValue) {
        this.dValue = dValue;
    }
    public Double getJValue() {
        return jValue;
    }

    public void setJValue(Double jValue) {
        this.jValue = jValue;
    }
    public Double getRsv() {
        return rsv;
    }

    public void setRsv(Double rsv) {
        this.rsv = rsv;
    }
    public String getKdjType() {
        return kdjType;
    }

    public void setKdjType(String kdjType) {
        this.kdjType = kdjType;
    }

    @Override
    public String toString() {
        return "StockDayKdj{" +
            "tsCode=" + tsCode +
            ", tradeDate=" + tradeDate +
            ", kValue=" + kValue +
            ", dValue=" + dValue +
            ", jValue=" + jValue +
            ", rsv=" + rsv +
            ", kdjType=" + kdjType +
        "}";
    }
}
