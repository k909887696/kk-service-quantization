package com.kk.business.quantization.model.vobase.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 个股分钟行情查询详情实体
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@Schema(description = "个股分钟行情查询详情实体")
public class DailyTimeDetailsReqVo implements Serializable {

    private static final long serialVersionUID = 1L;
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
    /**
    * 交易时间
    */
    @Schema(description = "交易时间")
    private String tradeTime;


}
