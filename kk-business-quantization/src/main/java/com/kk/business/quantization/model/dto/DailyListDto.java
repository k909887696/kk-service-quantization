package com.kk.business.quantization.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 个股日线行情列表返回实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@Schema(name = "个股日线行情列表返回实体", description = "个股日线行情")
public class DailyListDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 股票代码
    */
    @Schema(description = "股票代码")
    private String tsCode;
    /**
    * 交易日期
    */
    @Schema(description = "交易日期")
    private String tradeDate;
    /**
    * 开盘价
    */
    @Schema(description = "开盘价")
    private Double open;
    /**
    * 最高价
    */
    @Schema(description = "最高价")
    private Double high;
    /**
    * 最低价
    */
    @Schema(description = "最低价")
    private Double low;
    /**
    * 收盘价
    */
    @Schema(description = "收盘价")
    private Double close;
    /**
    * 上一日收盘价
    */
    @Schema(description = "上一日收盘价")
    private Double preClose;
    /**
    * 涨跌额
    */
    @Schema(description = "涨跌额")
    private Double change;
    /**
    * 涨跌幅%
    */
    @Schema(description = "涨跌幅%")
    private Double pctChg;
    /**
    * 成交额
    */
    @Schema(description = "成交额")
    private Double vol;
    /**
    * 成交量
    */
    @Schema(description = "成交量")
    private Double amount;


}
