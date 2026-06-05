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
 * 概念明细
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@TableName("concept_detail")
@Schema(description = "概念明细")
public class ConceptDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 概念编号
    */
    @Schema(description = "概念编号")
    @MppMultiId("concept_id")
    private String conceptId;

    /**
    * 概念名称
    */
    @Schema(description = "概念名称")
    @TableField("concept_name")
    private String conceptName;

    /**
    * 股票编号
    */
    @Schema(description = "股票编号")
    @MppMultiId("ts_code")
    private String tsCode;

    /**
    * 股票名称
    */
    @Schema(description = "股票名称")
    @TableField("name")
    private String name;

    /**
    * 加入日期
    */
    @Schema(description = "加入日期")
    @TableField("in_date")
    private String inDate;

    /**
    * 剔除日期
    */
    @Schema(description = "剔除日期")
    @TableField("out_date")
    private String outDate;

    /**
    * 股票代码
    */
    @Schema(description = "股票代码")
    @TableField("symbol")
    private String symbol;

    /**
    * 权重
    */
    @Schema(description = "权重")
    @TableField("weight")
    private String weight;


}
