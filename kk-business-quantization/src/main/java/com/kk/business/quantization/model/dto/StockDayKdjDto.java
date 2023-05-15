package com.kk.business.quantization.model.dto;

public class StockDayKdjDto {

    /**
     * ts代码
     */
    private String tsCode;
    /**
     * 交易日期
     */
    private String tradeDate;

    private double kValue ;

    private double dValue ;

    private double jValue ;

    /**
     * rsv
     */
    private double rsv;

    /**
     * kdj 类型（例如：9_3_3）
     */
    private String kdjType;

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

    public double getkValue() {
        return kValue;
    }

    public void setkValue(double kValue) {
        this.kValue = kValue;
    }

    public double getdValue() {
        return dValue;
    }

    public void setdValue(double dValue) {
        this.dValue = dValue;
    }

    public double getjValue() {
        return jValue;
    }

    public void setjValue(double jValue) {
        this.jValue = jValue;
    }

    public double getRsv() {
        return rsv;
    }

    public void setRsv(double rsv) {
        this.rsv = rsv;
    }

    public String getKdjType() {
        return kdjType;
    }

    public void setKdjType(String kdjType) {
        this.kdjType = kdjType;
    }
}
