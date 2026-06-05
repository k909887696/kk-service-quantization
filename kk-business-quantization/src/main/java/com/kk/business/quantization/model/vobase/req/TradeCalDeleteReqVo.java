package com.kk.business.quantization.model.vobase.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 交易日历删除实体
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@Schema(description = "交易日历删除实体")
public class TradeCalDeleteReqVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 交易所 SSE上交所 SZSE深交所
    */
    @Schema(description ="交易所 SSE上交所 SZSE深交所")
    private String exchange;
    /**
    * 日历日期
    */
    @Schema(description ="日历日期")
    private String calDate;


}
