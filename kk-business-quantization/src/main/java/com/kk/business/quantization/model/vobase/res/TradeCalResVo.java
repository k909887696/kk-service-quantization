package com.kk.business.quantization.model.vobase.res;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 交易日历详情返回实体
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@Schema( description = "交易日历详情返回实体")
public class TradeCalResVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 交易所 SSE上交所 SZSE深交所
    */
    @Schema(description = "交易所 SSE上交所 SZSE深交所")
    private String exchange;
    /**
    * 日历日期
    */
    @Schema(description = "日历日期")
    private String calDate;
    /**
    * 是否交易 0休市 1交易
    */
    @Schema(description = "是否交易 0休市 1交易")
    private String isOpen;
    /**
    * 上一个交易日
    */
    @Schema(description = "上一个交易日")
    private String pretradeDate;


}
