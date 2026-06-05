package com.kk.business.quantization.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
 import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
/**
 * <p>
 * 交易日历
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@TableName("trade_cal")
@Schema(description = "交易日历")
public class TradeCal implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 交易所 SSE上交所 SZSE深交所
    */
    @Schema(description = "交易所 SSE上交所 SZSE深交所")
    @MppMultiId("exchange")
    private String exchange;

    /**
    * 日历日期
    */
    @Schema(description = "日历日期")
    @MppMultiId("cal_date")
    private String calDate;

    /**
    * 是否交易 0休市 1交易
    */
    @Schema(description = "是否交易 0休市 1交易")
    @TableField("is_open")
    private String isOpen;

    /**
    * 上一个交易日
    */
    @Schema(description = "上一个交易日")
    @TableField("pretrade_date")
    private String pretradeDate;


}
