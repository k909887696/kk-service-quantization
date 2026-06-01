package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * kdj交叉点新增实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@Schema(name = "kdj交叉点新增实体", description = "kdj交叉点")
public class KdjCrossAddVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 股票编码
    */
    @Schema(description = "股票编码")
    private String tsCode;
    /**
    * 交易日期
    */
    @Schema(description = "交易日期")
    private String tradeDate;
    /**
    * 交叉点类型（UP:金叉,DOWN:死叉）
    */
    @Schema(description = "交叉点类型（UP:金叉,DOWN:死叉）")
    private String crossType;
    /**
    * 运算周期类型（默认 9_3_3）
    */
    @Schema(description = "运算周期类型（默认 9_3_3）")
    private String analysisType;


}
