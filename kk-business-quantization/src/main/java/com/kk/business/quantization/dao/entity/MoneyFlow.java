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
 * 个股资金流向
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@TableName("money_flow")
@ApiModel(value = "个股资金流向对象", description = "个股资金流向")
public class MoneyFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * TS代码
     */
    @ApiModelProperty("TS代码")
    @MppMultiId("ts_code")
    private String tsCode;

    /**
     * 交易日期
     */
    @ApiModelProperty("交易日期")
    @MppMultiId("trade_date")
    private String tradeDate;

    /**
     * 小单买入量（手）
     */
    @ApiModelProperty("小单买入量（手）")
    @TableField("buy_sm_vol")
    private Integer buySmVol;

    /**
     * 小单买入金额（万元）
     */
    @ApiModelProperty("小单买入金额（万元）")
    @TableField("buy_sm_amount")
    private Double buySmAmount;

    /**
     * 小单卖出量（手）
     */
    @ApiModelProperty("小单卖出量（手）")
    @TableField("sell_sm_vol")
    private Integer sellSmVol;

    /**
     * 小单卖出金额（万元）
     */
    @ApiModelProperty("小单卖出金额（万元）")
    @TableField("sell_sm_amount")
    private Double sellSmAmount;

    /**
     * 中单买入量（手）
     */
    @ApiModelProperty("中单买入量（手）")
    @TableField("buy_md_vol")
    private Integer buyMdVol;

    /**
     * 中单买入金额（万元）
     */
    @ApiModelProperty("中单买入金额（万元）")
    @TableField("buy_md_amount")
    private Double buyMdAmount;

    /**
     * 中单卖出量（手）
     */
    @ApiModelProperty("中单卖出量（手）")
    @TableField("sell_md_vol")
    private Integer sellMdVol;

    /**
     * 中单卖出金额（万元）
     */
    @ApiModelProperty("中单卖出金额（万元）")
    @TableField("sell_md_amount")
    private Double sellMdAmount;

    /**
     * 大单买入量（手）
     */
    @ApiModelProperty("大单买入量（手）")
    @TableField("buy_lg_vol")
    private Integer buyLgVol;

    /**
     * 大单买入金额（万元）
     */
    @ApiModelProperty("大单买入金额（万元）")
    @TableField("buy_lg_amount")
    private Double buyLgAmount;

    /**
     * 大单卖出量（手）
     */
    @ApiModelProperty("大单卖出量（手）")
    @TableField("sell_lg_vol")
    private Integer sellLgVol;

    /**
     * 大单卖出金额（万元）
     */
    @ApiModelProperty("大单卖出金额（万元）")
    @TableField("sell_lg_amount")
    private Double sellLgAmount;

    /**
     * 特大单买入量（手）
     */
    @ApiModelProperty("特大单买入量（手）")
    @TableField("buy_elg_vol")
    private Integer buyElgVol;

    /**
     * 特大单买入金额（万元）
     */
    @ApiModelProperty("特大单买入金额（万元）")
    @TableField("buy_elg_amount")
    private Double buyElgAmount;

    /**
     * 特大单卖出量（手）
     */
    @ApiModelProperty("特大单卖出量（手）")
    @TableField("sell_elg_vol")
    private Integer sellElgVol;

    /**
     * 特大单卖出金额（万元）
     */
    @ApiModelProperty("特大单卖出金额（万元）")
    @TableField("sell_elg_amount")
    private Double sellElgAmount;

    /**
     * 净流入量（手）
     */
    @ApiModelProperty("净流入量（手）")
    @TableField("net_mf_vol")
    private Integer netMfVol;

    /**
     * 净流入额（万元）
     */
    @ApiModelProperty("净流入额（万元）")
    @TableField("net_mf_amount")
    private Double netMfAmount;


}
