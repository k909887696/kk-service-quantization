package com.kk.business.quantization.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
 import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 市场基本信息
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@TableName("market_basic")
@Schema(description = "市场基本信息")
public class MarketBasic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 市场代码
    */
    @Schema(description = "市场代码")
    @TableId("market_code")
    private String marketCode;

    /**
    * 市场名称
    */
    @Schema(description = "市场名称")
    @TableField("market_name")
    private String marketName;

    /**
    * 更新时间
    */
    @Schema(description = "更新时间")
    @TableField("op_date")
    private Date opDate;


}
