package com.kk.business.quantization.model.po.dfcf;

import com.kk.common.base.model.BasePage;

import java.util.List;

/**
 * @Author: kk
 * @Date: 2021/12/11 11:37
 *  获取d东方财富概念资金流向接口请求参数
 */
public class DfcfConceptMoneyFlowVo extends BasePage {

    /**
     * 代码列表
     */
    private List<String> ids;
    /**
     * 概念代码
     */
    private  String conceptId;
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
    /**
     * 获取数据量 默认5000
     */
    private int limit;

    public int getLimit() {
        if(limit<=0)
            limit=5000;
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

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

    public String getConceptId() {
        return conceptId;
    }

    public void setConceptId(String conceptId) {
        this.conceptId = conceptId;
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
