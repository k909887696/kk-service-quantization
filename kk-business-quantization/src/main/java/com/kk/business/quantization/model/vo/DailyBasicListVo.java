package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.kk.common.base.model.BasePage;
/**
 * <p>
 * 个股每日指标	列表查询实体
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
@Data
@ApiModel(value = "个股每日指标	列表查询实体", description = "个股每日指标	")
public class DailyBasicListVo extends BasePage{

    private static final long serialVersionUID = 1L;

    /**
    * TS代码
    */
    @ApiModelProperty("TS代码")
    private String tsCode;
    /**
    * 交易日期开始
    */
    @ApiModelProperty("交易日期开始")
    private String tradeDateStart;
    /**
    * 交易日期结束
    */
    @ApiModelProperty("交易日期结束")
    private String tradeDateEnd;

    /**
    * 当日收盘价
    */
    @ApiModelProperty("当日收盘价")
    private Double close;
    /**
    * 换手率（%）
    */
    @ApiModelProperty("换手率（%）")
    private Double turnoverRate;
    /**
    * 换手率（自由流通股）
    */
    @ApiModelProperty("换手率（自由流通股）")
    private Double turnoverRateF;
    /**
    * 量比
    */
    @ApiModelProperty("量比")
    private Double volumeRatio;
    /**
    * 市盈率（总市值/净利润， 亏损的PE为空）
    */
    @ApiModelProperty("市盈率（总市值/净利润， 亏损的PE为空）")
    private Double pe;
    /**
    * 市盈率（TTM，亏损的PE为空）
    */
    @ApiModelProperty("市盈率（TTM，亏损的PE为空）")
    private Double peTtm;
    /**
    * 市净率（总市值/净资产）
    */
    @ApiModelProperty("市净率（总市值/净资产）")
    private Double pb;
    /**
    * 市销率
    */
    @ApiModelProperty("市销率")
    private Double ps;
    /**
    * 市销率（TTM）
    */
    @ApiModelProperty("市销率（TTM）")
    private Double psTtm;
    /**
    * 股息率 （%）
    */
    @ApiModelProperty("股息率 （%）")
    private Double dvRatio;
    /**
    * 股息率（TTM）（%）
    */
    @ApiModelProperty("股息率（TTM）（%）")
    private Double dvTtm;
    /**
    * 总股本 （万股）
    */
    @ApiModelProperty("总股本 （万股）")
    private Double totalShare;
    /**
    * 流通股本 （万股）
    */
    @ApiModelProperty("流通股本 （万股）")
    private Double floatShare;
    /**
    * 自由流通股本 （万）
    */
    @ApiModelProperty("自由流通股本 （万）")
    private Double freeShare;
    /**
    * 当日总市值（元）
    */
    @ApiModelProperty("当日总市值（元）")
    private Double totalMv;
    /**
    * 流通市值（万元）
    */
    @ApiModelProperty("流通市值（万元）")
    private Double circMv;


}
