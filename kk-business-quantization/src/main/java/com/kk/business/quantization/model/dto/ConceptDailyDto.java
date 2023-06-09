package com.kk.business.quantization.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 概念日线行情详情返回实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@ApiModel(value = "概念日线行情详情返回实体", description = "概念日线行情")
public class ConceptDailyDto implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 概念编号
    */
    @ApiModelProperty("概念编号")
    private String conceptId;
    /**
    * 交易日期
    */
    @ApiModelProperty("交易日期")
    private String tradeDate;
    /**
    * 开盘价
    */
    @ApiModelProperty("开盘价")
    private Double open;
    /**
    * 最高价
    */
    @ApiModelProperty("最高价")
    private Double high;
    /**
    * 最低价
    */
    @ApiModelProperty("最低价")
    private Double low;
    /**
    * 收盘价
    */
    @ApiModelProperty("收盘价")
    private Double close;
    /**
    * 前收盘价
    */
    @ApiModelProperty("前收盘价")
    private Double preClose;
    /**
    * 涨跌额
    */
    @ApiModelProperty("涨跌额")
    private Double change;
    /**
    * 涨跌幅%
    */
    @ApiModelProperty("涨跌幅%")
    private Double pctChg;
    /**
    * 成交量
    */
    @ApiModelProperty("成交量")
    private Double vol;
    /**
    * 成交额
    */
    @ApiModelProperty("成交额")
    private Double amount;


}
