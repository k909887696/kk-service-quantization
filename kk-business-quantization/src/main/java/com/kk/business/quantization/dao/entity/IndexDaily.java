package com.kk.business.quantization.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
/**
 * <p>
 * 指数日线行情
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@TableName("index_daily")
@ApiModel(value = "指数日线行情对象", description = "指数日线行情")
public class IndexDaily implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * TS指数代码
    */
    @ApiModelProperty("TS指数代码")
    @MppMultiId("ts_code")
    private String tsCode;

    /**
    * 交易日期
    */
    @ApiModelProperty("交易日期")
    @MppMultiId("trade_date")
    private String tradeDate;

    /**
    * 	收盘点位
    */
    @ApiModelProperty("	收盘点位")
    @TableField("close")
    private Double close;

    /**
    * 	开盘点位
    */
    @ApiModelProperty("	开盘点位")
    @TableField("open")
    private Double open;

    /**
    * 最高点位
    */
    @ApiModelProperty("最高点位")
    @TableField("high")
    private Double high;

    /**
    * 最低点位
    */
    @ApiModelProperty("最低点位")
    @TableField("low")
    private Double low;

    /**
    * 昨日收盘点
    */
    @ApiModelProperty("昨日收盘点")
    @TableField("pre_close")
    private Double preClose;

    /**
    * 涨跌点
    */
    @ApiModelProperty("涨跌点")
    @TableField("change")
    private Double change;

    /**
    * 涨跌幅（%）
    */
    @ApiModelProperty("涨跌幅（%）")
    @TableField("pct_chg")
    private Double pctChg;

    /**
    * 成交量（手）
    */
    @ApiModelProperty("成交量（手）")
    @TableField("vol")
    private Double vol;

    /**
    * 成交额（千元）
    */
    @ApiModelProperty("成交额（千元）")
    @TableField("amount")
    private Double amount;


}
