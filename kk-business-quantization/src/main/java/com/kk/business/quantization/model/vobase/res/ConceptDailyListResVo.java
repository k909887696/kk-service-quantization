package com.kk.business.quantization.model.vobase.res;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 概念日线行情列表返回实体
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@Schema( description = "概念日线行情列表返回实体")
public class ConceptDailyListResVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 概念编号
    */
    @Schema(description ="概念编号")
    private String conceptId;
    /**
    * 交易日期
    */
    @Schema(description ="交易日期")
    private String tradeDate;
    /**
    * 开盘价
    */
    @Schema(description ="开盘价")
    private Double open;
    /**
    * 最高价
    */
    @Schema(description ="最高价")
    private Double high;
    /**
    * 最低价
    */
    @Schema(description ="最低价")
    private Double low;
    /**
    * 收盘价
    */
    @Schema(description ="收盘价")
    private Double close;
    /**
    * 前收盘价
    */
    @Schema(description ="前收盘价")
    private Double preClose;
    /**
    * 涨跌额
    */
    @Schema(description ="涨跌额")
    private Double change;
    /**
    * 涨跌幅%
    */
    @Schema(description ="涨跌幅%")
    private Double pctChg;
    /**
    * 成交量
    */
    @Schema(description ="成交量")
    private Double vol;
    /**
    * 成交额
    */
    @Schema(description ="成交额")
    private Double amount;


}
