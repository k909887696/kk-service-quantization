package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.kk.common.base.model.BasePage;
/**
 * <p>
 * 个股基本信息列表查询实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@ApiModel(value = "个股基本信息列表查询实体", description = "个股基本信息")
public class StockBasicListVo extends BasePage{

    private static final long serialVersionUID = 1L;

    /**
    * ts股票代码
    */
    @ApiModelProperty("ts股票代码")
    private String tsCode;
    /**
    * 股票代码
    */
    @ApiModelProperty("股票代码")
    private String symbol;
    /**
    * 股票名称
    */
    @ApiModelProperty("股票名称")
    private String name;
    /**
    * 地域
    */
    @ApiModelProperty("地域")
    private String area;
    /**
    * 所属行业
    */
    @ApiModelProperty("所属行业")
    private String industry;
    /**
    * 股票全称
    */
    @ApiModelProperty("股票全称")
    private String fullname;
    /**
    * 英文全称
    */
    @ApiModelProperty("英文全称")
    private String enname;
    /**
    * 市场类型（主板/创业板/科创板/CDR）
    */
    @ApiModelProperty("市场类型（主板/创业板/科创板/CDR）")
    private String market;
    /**
    * 交易所代码
    */
    @ApiModelProperty("交易所代码")
    private String exchange;
    /**
    * 交易货币
    */
    @ApiModelProperty("交易货币")
    private String currType;
    /**
    * 上市状态 L上市 D退市 P暂停上市
    */
    @ApiModelProperty("上市状态 L上市 D退市 P暂停上市")
    private String listStatus;
    /**
    * 上市日期开始
    */
    @ApiModelProperty("上市日期开始")
    private String listDateStart;
    /**
    * 上市日期结束
    */
    @ApiModelProperty("上市日期结束")
    private String listDateEnd;

    /**
    * 退市日期开始
    */
    @ApiModelProperty("退市日期开始")
    private String delistDateStart;
    /**
    * 退市日期结束
    */
    @ApiModelProperty("退市日期结束")
    private String delistDateEnd;

    /**
    * 是否沪深港通标的，N否 H沪股通 S深股通
    */
    @ApiModelProperty("是否沪深港通标的，N否 H沪股通 S深股通")
    private String isHs;
    /**
    * 拼音缩写
    */
    @ApiModelProperty("拼音缩写")
    private String cnspell;
    /**
     * 概念分类代码列表
     */
    @ApiModelProperty("概念分类代码列表")
    private List<String> conceptIds;
    /**
     * kdj交叉日期
     */
    @ApiModelProperty("kdj交叉日期")
    private String kdjCrossDate;


}
