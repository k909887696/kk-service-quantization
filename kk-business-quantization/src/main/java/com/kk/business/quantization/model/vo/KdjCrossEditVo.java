package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * kdj交叉点	编辑实体
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
@Data
@ApiModel(value = "kdj交叉点	编辑实体", description = "kdj交叉点	")
public class KdjCrossEditVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 股票编码
    */
    @ApiModelProperty("股票编码")
    private String tsCode;
    /**
    * 交易日期
    */
    @ApiModelProperty("交易日期")
    private String tradeDate;
    /**
    * 交叉点类型（UP:金叉,DOWN:死叉）
    */
    @ApiModelProperty("交叉点类型（UP:金叉,DOWN:死叉）")
    private String crossType;
    /**
    * 运算周期类型（默认 9_3_3）
    */
    @ApiModelProperty("运算周期类型（默认 9_3_3）")
    private String analysisType;


}
