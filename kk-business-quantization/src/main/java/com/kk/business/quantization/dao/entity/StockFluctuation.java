package com.kk.business.quantization.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
 import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 个股异常波动信息
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@TableName("stock_fluctuation")
@Schema(description = "个股异常波动信息")
public class StockFluctuation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("code")
    private String code;

    @TableField("trade_date")
    private String tradeDate;

    @TableField("max_up_count")
    private Integer maxUpCount;

    @TableField("max_down_count")
    private Integer maxDownCount;

    @TableField("max_up_15d_count")
    private Integer maxUp15dCount;

    @TableField("max_down_15d_count")
    private Integer maxDown15dCount;


}
