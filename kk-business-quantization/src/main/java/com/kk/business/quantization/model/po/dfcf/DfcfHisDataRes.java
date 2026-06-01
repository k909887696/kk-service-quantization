package com.kk.business.quantization.model.po.dfcf;

import java.util.List;
import java.util.Map;

/**
 * @Author: kk
 * @Date: 2021/12/11 12:55
 * 东方概念历史数据格式
 */
public class DfcfHisDataRes {


    /**
     * 编码
     */
    private String code;
    /**
     * 市场
     */
    private String market;
    /**
     * 名称
     */
    private String name;
    /**
     * 总数量
     */
    private int dktotal;
    /**
     * 数据集合
     */
    private List<String> klines;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDktotal() {
        return dktotal;
    }

    public void setDktotal(int dktotal) {
        this.dktotal = dktotal;
    }

    public List<String> getKlines() {
        return klines;
    }

    public void setKlines(List<String> klines) {
        this.klines = klines;
    }
}
