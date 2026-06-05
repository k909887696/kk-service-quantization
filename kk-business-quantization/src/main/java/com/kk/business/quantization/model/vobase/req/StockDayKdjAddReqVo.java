package com.kk.business.quantization.model.vobase.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 个股kdj数据新增实体
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@Schema(description = "个股kdj数据")
public class StockDayKdjAddReqVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 股票编号
    */
    @Schema(description ="股票编号")
    private String tsCode;
    /**
    * 交易日期
    */
    @Schema(description ="交易日期")
    private String tradeDate;
    /**
    * k值
    */
    @Schema(description ="k值")
    private Double kValue;
    /**
    * d值
    */
    @Schema(description ="d值")
    private Double dValue;
    /**
    * j值
    */
    @Schema(description ="j值")
    private Double jValue;
    /**
    * rsv值
    */
    @Schema(description ="rsv值")
    private Double rsv;
    /**
    * kdj类型（默认 9_3_3）
    */
    @Schema(description ="kdj类型（默认 9_3_3）")
    private String kdjType;


}
