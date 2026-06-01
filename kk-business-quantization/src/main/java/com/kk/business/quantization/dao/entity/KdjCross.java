package com.kk.business.quantization.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

/**
 * <p>
 * kdj交叉点
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
@TableName("kdj_cross")
@Schema(name = "kdj交叉点对象", description = "kdj交叉点")
public class KdjCross implements Serializable {

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
    * 交叉类型（UP:金叉，DOWN：死叉）
    */
    @Schema(description = "交叉类型（UP:金叉，DOWN：死叉）")
    @TableField("cross_type")
    private String crossType;

    /**
    * kdj类型（9_3_3）
    */
    @Schema(description = "kdj类型（9_3_3）")
    @TableField("analysis_type")
    private String analysisType;

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
    public String getCrossType() {
        return crossType;
    }

    public void setCrossType(String crossType) {
        this.crossType = crossType;
    }
    public String getAnalysisType() {
        return analysisType;
    }

    public void setAnalysisType(String analysisType) {
        this.analysisType = analysisType;
    }

    @Override
    public String toString() {
        return "KdjCross{" +
            "tsCode=" + tsCode +
            ", tradeDate=" + tradeDate +
            ", crossType=" + crossType +
            ", analysisType=" + analysisType +
        "}";
    }
}
