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
 * 个股每日指标列表查询实体
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@Schema(description = "个股每日指标列表查询实体")
public class DailyBasicListReqVo extends BasePage{

    private static final long serialVersionUID = 1L;

    /**
    * TS代码
    */
    @Schema(description = "TS代码")
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
    * 当日收盘价
    */
    @Schema(description = "当日收盘价")
    private Double close;
    /**
    * 换手率（%）
    */
    @Schema(description = "换手率（%）")
    private Double turnoverRate;
    /**
    * 换手率（自由流通股）
    */
    @Schema(description = "换手率（自由流通股）")
    private Double turnoverRateF;
    /**
    * 量比
    */
    @Schema(description = "量比")
    private Double volumeRatio;
    /**
    * 市盈率（总市值/净利润， 亏损的PE为空）
    */
    @Schema(description = "市盈率（总市值/净利润， 亏损的PE为空）")
    private Double pe;
    /**
    * 市盈率（TTM，亏损的PE为空）
    */
    @Schema(description = "市盈率（TTM，亏损的PE为空）")
    private Double peTtm;
    /**
    * 市净率（总市值/净资产）
    */
    @Schema(description = "市净率（总市值/净资产）")
    private Double pb;
    /**
    * 市销率
    */
    @Schema(description = "市销率")
    private Double ps;
    /**
    * 市销率（TTM）
    */
    @Schema(description = "市销率（TTM）")
    private Double psTtm;
    /**
    * 股息率 （%）
    */
    @Schema(description = "股息率 （%）")
    private Double dvRatio;
    /**
    * 股息率（TTM）（%）
    */
    @Schema(description = "股息率（TTM）（%）")
    private Double dvTtm;
    /**
    * 总股本 （万股）
    */
    @Schema(description = "总股本 （万股）")
    private Double totalShare;
    /**
    * 流通股本 （万股）
    */
    @Schema(description = "流通股本 （万股）")
    private Double floatShare;
    /**
    * 自由流通股本 （万）
    */
    @Schema(description = "自由流通股本 （万）")
    private Double freeShare;
    /**
    * 当日总市值（元）
    */
    @Schema(description = "当日总市值（元）")
    private Double totalMv;
    /**
    * 流通市值（万元）
    */
    @Schema(description = "流通市值（万元）")
    private Double circMv;


}
