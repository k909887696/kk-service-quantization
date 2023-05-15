package com.kk.business.quantization.model.po.tushare;

public class CnmVo {

    /**
     * 股票代码（支持多个股票同时提取，逗号分隔）
     */
    private  String m;
    /**
     * 交易日期（YYYYMMDD）
     */
    private String startM;

    /**
     * 	结束日期(YYYYMMDD)
     */
    private String endM;

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getStartM() {
        return startM;
    }

    public void setStartM(String startM) {
        this.startM = startM;
    }

    public String getEndM() {
        return endM;
    }

    public void setEndM(String endM) {
        this.endM = endM;
    }
}
