package com.kk.business.quantization.model.po.tushare;

import com.kk.common.base.model.BasePage;

import java.util.List;

/**
 * @Author: kk
 * @Date: 2021/12/11 11:37
 *  获取资金流向接口请求参数
 */
public class MoneyFlowVo extends BasePage {

    /**
     * tscode列表
     */
    private List<String> ids;
    /**
     * 股票代码（支持多个股票同时提取，逗号分隔）
     */
    private  String tsCode;
    /**
     * 交易日期 20180506
     */
    private String tradeDate;
    /**
     * 交易日期（YYYYMMDD）
     */
    private String startDate;

    /**
     * 	结束日期(YYYYMMDD)
     */
    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTsCode() {
        return tsCode;
    }

    public void setTsCode(String tsCode) {
        this.tsCode = tsCode;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
