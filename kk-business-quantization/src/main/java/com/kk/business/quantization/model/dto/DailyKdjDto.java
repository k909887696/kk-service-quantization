package com.kk.business.quantization.model.dto;

import com.kk.business.quantization.dao.entity.Daily;

public class DailyKdjDto extends Daily {

    /**
     * 股票名称
     */
    private String name;
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
    /**
     * 最小值
     */
    private double minPrice ;

    /**
     * 最大值
     */
    private double maxPrice;
    /**
     * kdj交叉类型
     */
    private String crossType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCrossType() {
        return crossType;
    }

    public void setCrossType(String crossType) {
        this.crossType = crossType;
    }

    public double getKValue() {
        return kValue;
    }

    public void setKValue(double kValue) {
        this.kValue = kValue;
    }

    public double getDValue() {
        return dValue;
    }

    public void setDValue(double dValue) {
        this.dValue = dValue;
    }

    public double getJValue() {
        return jValue;
    }

    public void setJValue(double jValue) {
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

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }
}
