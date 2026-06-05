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
 * 指数成分权重
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@TableName("index_weight")
@Schema(description = "指数成分权重")
public class IndexWeight implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 指数代码
    */
    @Schema(description = "指数代码")
    @MppMultiId("index_code")
    private String indexCode;

    /**
    * 成分代码
    */
    @Schema(description = "成分代码")
    @MppMultiId("con_code")
    private String conCode;

    /**
    * 交易日期
    */
    @Schema(description = "交易日期")
    @TableField("trade_date")
    private String tradeDate;

    /**
    * 权重
    */
    @Schema(description = "权重")
    @TableField("weight")
    private Double weight;


}
