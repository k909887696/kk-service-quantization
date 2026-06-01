package com.kk.business.quantization.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
 import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 人民币货币总量对象
 * </p>
 *
 * @author kk
 * @since 2026-05-30
 */
@Data
@TableName("cn_m")
@Schema(description = "人民币货币总量对象")
public class CnM implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 月份
    */
    @Schema(description = "月份")
    @TableId("month")
    private String month;

    /**
    * 货币M0总量
    */
    @Schema(description = "货币M0总量")
    @TableField("m0")
    private Double m0;

    /**
    * 货币M0同比
    */
    @Schema(description = "货币M0同比")
    @TableField("m0_yoy")
    private Double m0Yoy;

    /**
    * 货币M0环比
    */
    @Schema(description = "货币M0环比")
    @TableField("m0_mom")
    private Double m0Mom;

    /**
    * 货币M1总量
    */
    @Schema(description = "货币M1总量")
    @TableField("m1")
    private Double m1;

    /**
    * 货币M1同比
    */
    @Schema(description = "货币M1同比")
    @TableField("m1_yoy")
    private Double m1Yoy;

    /**
    * 货币M1环比
    */
    @Schema(description = "货币M1环比")
    @TableField("m1_mom")
    private Double m1Mom;

    /**
    * 货币M2总量
    */
    @Schema(description = "货币M2总量")
    @TableField("m2")
    private Double m2;

    /**
    * 货币M2同比
    */
    @Schema(description = "货币M2同比")
    @TableField("m2_yoy")
    private Double m2Yoy;

    /**
    * 货币M2环比
    */
    @Schema(description = "货币M2环比")
    @TableField("m2_mom")
    private Double m2Mom;


}
