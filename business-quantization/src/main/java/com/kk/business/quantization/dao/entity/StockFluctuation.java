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
 * 股票异动信息
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
@TableName("stock_fluctuation")
@ApiModel(value = "股票异动信息对象", description = "股票异动信息")
public class StockFluctuation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * ts股票代码
    */
    @ApiModelProperty("ts股票代码")
    @MppMultiId("code")
    private String code;

    /**
    * 交易日期
    */
    @ApiModelProperty("交易日期")
    @MppMultiId("trade_date")
    private String tradeDate;

    /**
    * 连板次数
    */
    @ApiModelProperty("连板次数")
    @TableField("max_up_count")
    private Integer maxUpCount;

    /**
    * 连跌次数
    */
    @ApiModelProperty("连跌次数")
    @TableField("max_down_count")
    private Integer maxDownCount;

    /**
    * 15天内涨停次数
    */
    @ApiModelProperty("15天内涨停次数")
    @TableField("max_up_15d_count")
    private Integer maxUp15dCount;

    /**
    * 15天内跌停次数
    */
    @ApiModelProperty("15天内跌停次数")
    @TableField("max_down_15d_count")
    private Integer maxDown15dCount;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }
    public Integer getMaxUpCount() {
        return maxUpCount;
    }

    public void setMaxUpCount(Integer maxUpCount) {
        this.maxUpCount = maxUpCount;
    }
    public Integer getMaxDownCount() {
        return maxDownCount;
    }

    public void setMaxDownCount(Integer maxDownCount) {
        this.maxDownCount = maxDownCount;
    }
    public Integer getMaxUp15dCount() {
        return maxUp15dCount;
    }

    public void setMaxUp15dCount(Integer maxUp15dCount) {
        this.maxUp15dCount = maxUp15dCount;
    }
    public Integer getMaxDown15dCount() {
        return maxDown15dCount;
    }

    public void setMaxDown15dCount(Integer maxDown15dCount) {
        this.maxDown15dCount = maxDown15dCount;
    }

    @Override
    public String toString() {
        return "StockFluctuation{" +
            "code=" + code +
            ", tradeDate=" + tradeDate +
            ", maxUpCount=" + maxUpCount +
            ", maxDownCount=" + maxDownCount +
            ", maxUp15dCount=" + maxUp15dCount +
            ", maxDown15dCount=" + maxDown15dCount +
        "}";
    }
}
