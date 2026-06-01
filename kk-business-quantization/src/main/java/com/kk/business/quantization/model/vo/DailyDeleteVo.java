package com.kk.business.quantization.model.vo;


import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 个股日线行情删除实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@Schema(name = "个股日线行情删除实体", description = "个股日线行情")
public class DailyDeleteVo implements Serializable {


    /**
    * 股票代码
    */
    @Schema(description = "股票代码")
    private String tsCode;
    /**
    * 交易日期
    */
    @Schema(description = "交易日期")
    private String tradeDate;


}
