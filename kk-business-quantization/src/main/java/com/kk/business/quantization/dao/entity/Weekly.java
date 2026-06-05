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
 * 个股周线行情
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@TableName("weekly")
@Schema(description = "个股周线行情")
public class Weekly implements Serializable {

    private static final long serialVersionUID = 1L;

    @MppMultiId("ts_code")
    private String tsCode;

    @MppMultiId("trade_date")
    private String tradeDate;

    @TableField("open")
    private Double open;

    @TableField("high")
    private Double high;

    @TableField("low")
    private Double low;

    @TableField("close")
    private Double close;

    @TableField("pre_close")
    private Double preClose;

    @TableField("change")
    private Double change;

    @TableField("pct_chg")
    private Double pctChg;

    @TableField("vol")
    private Double vol;

    @TableField("amount")
    private Double amount;


}
