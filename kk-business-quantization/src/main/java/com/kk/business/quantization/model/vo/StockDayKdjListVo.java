package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.kk.common.base.model.BasePage;
/**
 * <p>
 * 个股kdj数据列表查询实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@Schema(name = "个股kdj数据列表查询实体", description = "个股kdj数据")
public class StockDayKdjListVo extends BasePage{

    private static final long serialVersionUID = 1L;

    /**
    * 股票编号
    */
    @Schema(description = "股票编号")
    private String tsCode;
    /**
    * 交易日期开始
    */
    @Schema(description = "交易日期开始")
    private String tradeDateStart;
    /**
    * 交易日期结束
    */
    @Schema(description = "交易日期结束")
    private String tradeDateEnd;

    /**
    * k值
    */
    @Schema(description = "k值")
    private Double kValue;
    /**
    * d值
    */
    @Schema(description = "d值")
    private Double dValue;
    /**
    * j值
    */
    @Schema(description = "j值")
    private Double jValue;
    /**
    * rsv值
    */
    @Schema(description = "rsv值")
    private Double rsv;
    /**
    * kdj类型（默认 9_3_3）
    */
    @Schema(description = "kdj类型（默认 9_3_3）")
    private String kdjType;


}
