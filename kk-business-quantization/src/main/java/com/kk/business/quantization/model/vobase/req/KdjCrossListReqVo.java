package com.kk.business.quantization.model.vobase.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.kk.common.base.model.BasePage;
/**
 * <p>
 * kdj交叉点列表查询实体
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@Schema(description = "kdj交叉点列表查询实体")
public class KdjCrossListReqVo extends BasePage{

    private static final long serialVersionUID = 1L;

    /**
    * 股票编码
    */
    @Schema(description = "股票编码")
    private String tsCode;
    /**
    * 交易日期开始
    */
    @Schema(description ="交易日期开始")
    private String tradeDateStart;
    /**
    * 交易日期结束
    */
    @Schema(description ="交易日期结束")
    private String tradeDateEnd;

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
