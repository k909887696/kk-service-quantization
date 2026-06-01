package com.kk.business.quantization.model.po.tushare;

public class IndexBasicVo {

    /**
     * 指数代码
     */
    private  String tsCode;
    /**
     * 指数简称
     */
    private  String name;
    /**
     * 交易所或服务商(默认SSE)
     * MSCI	MSCI指数
     * CSI	中证指数
     * SSE	上交所指数
     * SZSE	深交所指数
     * CICC	中金指数
     * SW	申万指数
     * OTH	其他指数
     */
    private  String market;
    /**
     * 发布商
     */
    private String publisher;

    /**
     * 	指数类别
     */
    private String category;

    public String getTsCode() {
        return tsCode;
    }

    public void setTsCode(String tsCode) {
        this.tsCode = tsCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
