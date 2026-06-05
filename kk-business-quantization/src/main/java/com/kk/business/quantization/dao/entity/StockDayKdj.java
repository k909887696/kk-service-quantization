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
 * 个股kdj数据
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@TableName("stock_day_kdj")
@Schema(description = "个股kdj数据")
public class StockDayKdj implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 股票编号
    */
    @Schema(description = "股票编号")
    @MppMultiId("ts_code")
    private String tsCode;

    /**
    * 交易日期
    */
    @Schema(description = "交易日期")
    @MppMultiId("trade_date")
    private String tradeDate;

    /**
    * k值
    */
    @Schema(description = "k值")
    @TableField("k_value")
    private Double kValue;

    /**
    * d值
    */
    @Schema(description = "d值")
    @TableField("d_value")
    private Double dValue;

    /**
    * j值
    */
    @Schema(description = "j值")
    @TableField("j_value")
    private Double jValue;

    /**
    * rsv值
    */
    @Schema(description = "rsv值")
    @TableField("rsv")
    private Double rsv;

    /**
    * kdj类型（默认 9_3_3）
    */
    @Schema(description = "kdj类型（默认 9_3_3）")
    @TableField("kdj_type")
    private String kdjType;


}
