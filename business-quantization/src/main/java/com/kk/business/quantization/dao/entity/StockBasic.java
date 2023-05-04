package com.kk.business.quantization.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 个股基本信息
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
@TableName("stock_basic")
@ApiModel(value = "个股基本信息对象", description = "个股基本信息")
public class StockBasic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * ts股票代码
    */
    @ApiModelProperty("ts股票代码")
    @TableId("ts_code")
    private String tsCode;

    /**
    * 股票代码
    */
    @ApiModelProperty("股票代码")
    @TableField("symbol")
    private String symbol;

    /**
    * 股票名称
    */
    @ApiModelProperty("股票名称")
    @TableField("name")
    private String name;

    /**
    * 地域
    */
    @ApiModelProperty("地域")
    @TableField("area")
    private String area;

    /**
    * 所属行业
    */
    @ApiModelProperty("所属行业")
    @TableField("industry")
    private String industry;

    /**
    * 股票全称
    */
    @ApiModelProperty("股票全称")
    @TableField("fullname")
    private String fullname;

    /**
    * 英文全称
    */
    @ApiModelProperty("英文全称")
    @TableField("enname")
    private String enname;

    /**
    * 拼音缩写
    */
    @ApiModelProperty("拼音缩写")
    @TableField("cnspell")
    private String cnspell;

    /**
    * 市场类型（主板/创业板/科创板/CDR）
    */
    @ApiModelProperty("市场类型（主板/创业板/科创板/CDR）")
    @TableField("market")
    private String market;

    /**
    * 交易所代码
    */
    @ApiModelProperty("交易所代码")
    @TableField("exchange")
    private String exchange;

    /**
    * 交易货币
    */
    @ApiModelProperty("交易货币")
    @TableField("curr_type")
    private String currType;

    /**
    * 上市状态 L上市 D退市 P暂停上市
    */
    @ApiModelProperty("上市状态 L上市 D退市 P暂停上市")
    @TableField("list_status")
    private String listStatus;

    /**
    * 上市日期
    */
    @ApiModelProperty("上市日期")
    @TableField("list_date")
    private String listDate;

    /**
    * 退市日期
    */
    @ApiModelProperty("退市日期")
    @TableField("delist_date")
    private String delistDate;

    /**
    * 是否沪深港通标的，N否 H沪股通 S深股通
    */
    @ApiModelProperty("是否沪深港通标的，N否 H沪股通 S深股通")
    @TableField("is_hs")
    private String isHs;

    public String getTsCode() {
        return tsCode;
    }

    public void setTsCode(String tsCode) {
        this.tsCode = tsCode;
    }
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname;
    }
    public String getCnspell() {
        return cnspell;
    }

    public void setCnspell(String cnspell) {
        this.cnspell = cnspell;
    }
    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }
    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
    public String getCurrType() {
        return currType;
    }

    public void setCurrType(String currType) {
        this.currType = currType;
    }
    public String getListStatus() {
        return listStatus;
    }

    public void setListStatus(String listStatus) {
        this.listStatus = listStatus;
    }
    public String getListDate() {
        return listDate;
    }

    public void setListDate(String listDate) {
        this.listDate = listDate;
    }
    public String getDelistDate() {
        return delistDate;
    }

    public void setDelistDate(String delistDate) {
        this.delistDate = delistDate;
    }
    public String getIsHs() {
        return isHs;
    }

    public void setIsHs(String isHs) {
        this.isHs = isHs;
    }

    @Override
    public String toString() {
        return "StockBasic{" +
            "tsCode=" + tsCode +
            ", symbol=" + symbol +
            ", name=" + name +
            ", area=" + area +
            ", industry=" + industry +
            ", fullname=" + fullname +
            ", enname=" + enname +
            ", cnspell=" + cnspell +
            ", market=" + market +
            ", exchange=" + exchange +
            ", currType=" + currType +
            ", listStatus=" + listStatus +
            ", listDate=" + listDate +
            ", delistDate=" + delistDate +
            ", isHs=" + isHs +
        "}";
    }
}
