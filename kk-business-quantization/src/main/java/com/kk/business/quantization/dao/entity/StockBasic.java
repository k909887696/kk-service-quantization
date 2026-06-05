package com.kk.business.quantization.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
 import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 个股基本信息
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@TableName("stock_basic")
@Schema(description = "个股基本信息")
public class StockBasic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * ts股票代码
    */
    @Schema(description = "ts股票代码")
    @TableId("ts_code")
    private String tsCode;

    /**
    * 股票代码
    */
    @Schema(description = "股票代码")
    @TableField("symbol")
    private String symbol;

    /**
    * 股票名称
    */
    @Schema(description = "股票名称")
    @TableField("name")
    private String name;

    /**
    * 地域
    */
    @Schema(description = "地域")
    @TableField("area")
    private String area;

    /**
    * 所属行业
    */
    @Schema(description = "所属行业")
    @TableField("industry")
    private String industry;

    /**
    * 股票全称
    */
    @Schema(description = "股票全称")
    @TableField("fullname")
    private String fullname;

    /**
    * 英文全称
    */
    @Schema(description = "英文全称")
    @TableField("enname")
    private String enname;

    /**
    * 市场类型（主板/创业板/科创板/CDR）
    */
    @Schema(description = "市场类型（主板/创业板/科创板/CDR）")
    @TableField("market")
    private String market;

    /**
    * 交易所代码
    */
    @Schema(description = "交易所代码")
    @TableField("exchange")
    private String exchange;

    /**
    * 交易货币
    */
    @Schema(description = "交易货币")
    @TableField("curr_type")
    private String currType;

    /**
    * 上市状态 L上市 D退市 P暂停上市
    */
    @Schema(description = "上市状态 L上市 D退市 P暂停上市")
    @TableField("list_status")
    private String listStatus;

    /**
    * 上市日期
    */
    @Schema(description = "上市日期")
    @TableField("list_date")
    private String listDate;

    /**
    * 退市日期
    */
    @Schema(description = "退市日期")
    @TableField("delist_date")
    private String delistDate;

    /**
    * 是否沪深港通标的，N否 H沪股通 S深股通
    */
    @Schema(description = "是否沪深港通标的，N否 H沪股通 S深股通")
    @TableField("is_hs")
    private String isHs;

    /**
    * 拼音缩写
    */
    @Schema(description = "拼音缩写")
    @TableField("cnspell")
    private String cnspell;


}
