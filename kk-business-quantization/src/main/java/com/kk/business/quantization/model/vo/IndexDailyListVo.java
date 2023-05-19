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
 * 指数日线行情列表查询实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@ApiModel(value = "指数日线行情列表查询实体", description = "指数日线行情")
public class IndexDailyListVo extends BasePage{

    private static final long serialVersionUID = 1L;

    /**
    * TS指数代码
    */
    @ApiModelProperty("TS指数代码")
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
    * 	收盘点位
    */
    @ApiModelProperty("	收盘点位")
    private Double close;
    /**
    * 	开盘点位
    */
    @ApiModelProperty("	开盘点位")
    private Double open;
    /**
    * 最高点位
    */
    @ApiModelProperty("最高点位")
    private Double high;
    /**
    * 最低点位
    */
    @ApiModelProperty("最低点位")
    private Double low;
    /**
    * 昨日收盘点
    */
    @ApiModelProperty("昨日收盘点")
    private Double preClose;
    /**
    * 涨跌点
    */
    @ApiModelProperty("涨跌点")
    private Double change;
    /**
    * 涨跌幅（%）
    */
    @ApiModelProperty("涨跌幅（%）")
    private Double pctChg;
    /**
    * 成交量（手）
    */
    @ApiModelProperty("成交量（手）")
    private Double vol;
    /**
    * 成交额（千元）
    */
    @ApiModelProperty("成交额（千元）")
    private Double amount;


}
