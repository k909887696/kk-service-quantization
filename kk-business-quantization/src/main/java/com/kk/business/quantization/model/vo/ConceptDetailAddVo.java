package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 概念明细新增实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@Schema(name = "概念明细新增实体", description = "概念明细")
public class ConceptDetailAddVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 概念编号
    */
    @Schema(description = "概念编号")
    private String conceptId;
    /**
    * 概念名称
    */
    @Schema(description = "概念名称")
    private String conceptName;
    /**
    * 股票编号
    */
    @Schema(description = "股票编号")
    private String tsCode;
    /**
    * 股票名称
    */
    @Schema(description = "股票名称")
    private String name;
    /**
    * 加入日期
    */
    @Schema(description = "加入日期")
    private String inDate;
    /**
    * 剔除日期
    */
    @Schema(description = "剔除日期")
    private String outDate;
    /**
    * 股票代码
    */
    @Schema(description = "股票代码")
    private String symbol;
    /**
    * 权重
    */
    @Schema(description = "权重")
    private String weight;


}
