package com.kk.business.quantization.model.po.tushare;

import com.kk.common.base.model.BasePage;

/**
 * @Author: kk
 * @Date: 2021/12/11 11:37
 *  获取个股基本信息请求参数
 */
public class StockBasicVo   {

    /**
     * 是否沪深港通标的，N否 H沪股通 S深股通
     */
    private  String isHs;
    /**
     * 上市状态 L上市 D退市 P暂停上市，默认是L
     */
    private String listStatus;
    /**
     * 交易所 SSE上交所 SZSE深交所 BSE北交所
     */
    private String exchange;
    /**
     * TS股票代码
     */
    private String tsCode;
    /**
     * 市场类别 （主板/创业板/科创板/CDR/北交所）
     */
    private String market;
    /**
     * 截取数量
     */
    private int limit;
    /**
     * 偏移量
     */
    private int offset;

    /**
     * 	名称
     */
    private String name;

    /**
     * 更新类型（cover：覆盖；add：叠加） 默认：add
     */
    private String updateType;

    public String getIsHs() {
        return isHs;
    }

    public void setIsHs(String isHs) {
        this.isHs = isHs;
    }

    public String getListStatus() {
        return listStatus;
    }

    public void setListStatus(String listStatus) {
        this.listStatus = listStatus;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getTsCode() {
        return tsCode;
    }

    public void setTsCode(String tsCode) {
        this.tsCode = tsCode;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdateType() {
        return updateType;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }
}
