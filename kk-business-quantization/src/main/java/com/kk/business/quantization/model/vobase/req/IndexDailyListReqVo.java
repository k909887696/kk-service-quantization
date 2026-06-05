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
 * 指数日线行情列表查询实体
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@Schema(description = "指数日线行情列表查询实体")
public class IndexDailyListReqVo extends BasePage{

    private static final long serialVersionUID = 1L;

    /**
    * TS指数代码
    */
    @Schema(description = "TS指数代码")
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
    * 	收盘点位
    */
    @Schema(description = "	收盘点位")
    private Double close;
    /**
    * 	开盘点位
    */
    @Schema(description = "	开盘点位")
    private Double open;
    /**
    * 最高点位
    */
    @Schema(description = "最高点位")
    private Double high;
    /**
    * 最低点位
    */
    @Schema(description = "最低点位")
    private Double low;
    /**
    * 昨日收盘点
    */
    @Schema(description = "昨日收盘点")
    private Double preClose;
    /**
    * 涨跌点
    */
    @Schema(description = "涨跌点")
    private Double change;
    /**
    * 涨跌幅（%）
    */
    @Schema(description = "涨跌幅（%）")
    private Double pctChg;
    /**
    * 成交量（手）
    */
    @Schema(description = "成交量（手）")
    private Double vol;
    /**
    * 成交额（千元）
    */
    @Schema(description = "成交额（千元）")
    private Double amount;


}
