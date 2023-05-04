package com.kk.business.quantization.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 各个市场涨跌幅限制
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
@TableName("max_pct_chg")
@ApiModel(value = "各个市场涨跌幅限制对象", description = "各个市场涨跌幅限制")
public class MaxPctChg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 市场
    */
    @ApiModelProperty("市场")
    @TableId("market")
    private String market;

    /**
    * 涨跌幅限制
    */
    @ApiModelProperty("涨跌幅限制")
    @TableField("max_pct_chg")
    private Double maxPctChg;

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }
    public Double getMaxPctChg() {
        return maxPctChg;
    }

    public void setMaxPctChg(Double maxPctChg) {
        this.maxPctChg = maxPctChg;
    }

    @Override
    public String toString() {
        return "MaxPctChg{" +
            "market=" + market +
            ", maxPctChg=" + maxPctChg +
        "}";
    }
}
