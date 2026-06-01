package com.kk.business.quantization.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 概念资金流向列表返回实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@Schema(name = "概念资金流向列表返回实体", description = "概念资金流向")
public class ConceptMoneyFlowListDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 概念代码
    */
    @Schema(description = "概念代码")
    private String code;
    /**
    * 交易日期
    */
    @Schema(description = "交易日期")
    private String tradeDate;
    /**
    * 小单买入量（手）
    */
    @Schema(description = "小单买入量（手）")
    private Integer buySmVol;
    /**
    * 小单买入金额（万元）
    */
    @Schema(description = "小单买入金额（万元）")
    private Double buySmAmount;
    /**
    * 小单卖出量（手）
    */
    @Schema(description = "小单卖出量（手）")
    private Integer sellSmVol;
    /**
    * 小单卖出金额（万元）
    */
    @Schema(description = "小单卖出金额（万元）")
    private Double sellSmAmount;
    /**
    * 中单买入量（手）
    */
    @Schema(description = "中单买入量（手）")
    private Integer buyMdVol;
    /**
    * 中单买入金额（万元）
    */
    @Schema(description = "中单买入金额（万元）")
    private Double buyMdAmount;
    /**
    * 中单卖出量（手）
    */
    @Schema(description = "中单卖出量（手）")
    private Integer sellMdVol;
    /**
    * 中单卖出金额（万元）
    */
    @Schema(description = "中单卖出金额（万元）")
    private Double sellMdAmount;
    /**
    * 大单买入量（手）
    */
    @Schema(description = "大单买入量（手）")
    private Integer buyLgVol;
    /**
    * 大单买入金额（万元）
    */
    @Schema(description = "大单买入金额（万元）")
    private Double buyLgAmount;
    /**
    * 大单卖出量（手）
    */
    @Schema(description = "大单卖出量（手）")
    private Integer sellLgVol;
    /**
    * 大单卖出金额（万元）
    */
    @Schema(description = "大单卖出金额（万元）")
    private Double sellLgAmount;
    /**
    * 特大单买入量（手）
    */
    @Schema(description = "特大单买入量（手）")
    private Integer buyElgVol;
    /**
    * 特大单买入金额（万元）
    */
    @Schema(description = "特大单买入金额（万元）")
    private Double buyElgAmount;
    /**
    * 特大单卖出量（手）
    */
    @Schema(description = "特大单卖出量（手）")
    private Integer sellElgVol;
    /**
    * 特大单卖出金额（万元）
    */
    @Schema(description = "特大单卖出金额（万元）")
    private Double sellElgAmount;
    /**
    * 净流入量（手）
    */
    @Schema(description = "净流入量（手）")
    private Integer netMfVol;
    /**
    * 净流入额（万元）
    */
    @Schema(description = "净流入额（万元）")
    private Double netMfAmount;
    /**
    * 概念名称
    */
    @Schema(description = "概念名称")
    private String name;


}
