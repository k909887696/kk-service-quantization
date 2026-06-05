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
 * kdj交叉点
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@TableName("kdj_cross")
@Schema(description = "kdj交叉点")
public class KdjCross implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 股票编码
    */
    @Schema(description = "股票编码")
    @MppMultiId("ts_code")
    private String tsCode;

    /**
    * 交易日期
    */
    @Schema(description = "交易日期")
    @MppMultiId("trade_date")
    private String tradeDate;

    /**
    * 交叉点类型（UP:金叉,DOWN:死叉）
    */
    @Schema(description = "交叉点类型（UP:金叉,DOWN:死叉）")
    @TableField("cross_type")
    private String crossType;

    /**
    * 运算周期类型（默认 9_3_3）
    */
    @Schema(description = "运算周期类型（默认 9_3_3）")
    @TableField("analysis_type")
    private String analysisType;


}
