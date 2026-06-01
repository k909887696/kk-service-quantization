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
 * 交易日历列表查询实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@Schema(name = "交易日历列表查询实体", description = "交易日历")
public class TradeCalListVo extends BasePage{

    private static final long serialVersionUID = 1L;

    /**
    * 交易所 SSE上交所 SZSE深交所
    */
    @Schema(description = "交易所 SSE上交所 SZSE深交所")
    private String exchange;
    /**
    * 日历日期开始
    */
    @Schema(description = "日历日期开始")
    private String calDateStart;
    /**
    * 日历日期结束
    */
    @Schema(description = "日历日期结束")
    private String calDateEnd;

    /**
    * 是否交易 0休市 1交易
    */
    @Schema(description = "是否交易 0休市 1交易")
    private String isOpen;
    /**
    * 上一个交易日开始
    */
    @Schema(description = "上一个交易日开始")
    private String pretradeDateStart;
    /**
    * 上一个交易日结束
    */
    @Schema(description = "上一个交易日结束")
    private String pretradeDateEnd;



}
