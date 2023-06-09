package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 个股日线行情新增实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@ApiModel(value = "个股日线行情新增实体", description = "个股日线行情")
public class DailyAddVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 股票代码
    */
    @ApiModelProperty("股票代码")
    private String tsCode;
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
    * 上一日收盘价
    */
    @ApiModelProperty("上一日收盘价")
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
    * 成交额
    */
    @ApiModelProperty("成交额")
    private Double vol;
    /**
    * 成交量
    */
    @ApiModelProperty("成交量")
    private Double amount;


}
