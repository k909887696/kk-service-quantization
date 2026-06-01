package com.kk.business.quantization.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: kk
 * @Date: 2021/12/11 11:27
 */
@Component
@ConfigurationProperties("third-data-config")
public class ThirdDataConfig {

    /**
     * tushare api地址
     */
    private String tushareApiUrl;

    /**
     * tushare token
     */
    private String tushareToken;

    /**
     * 东方财富实时数据域名地址
     */
    private String dfcfApiUrl;
    /**
     * 东方财富历史数据域名地址
     */
    private String dfcfHisApiUrl;
    /**
     * 东方财富cb，用于数据格式化
     */
    private String dfcfCb;


    public String getDfcfApiUrl() {
        return dfcfApiUrl;
    }

    public void setDfcfApiUrl(String dfcfApiUrl) {
        this.dfcfApiUrl = dfcfApiUrl;
    }

    public String getDfcfHisApiUrl() {
        return dfcfHisApiUrl;
    }

    public void setDfcfHisApiUrl(String dfcfHisApiUrl) {
        this.dfcfHisApiUrl = dfcfHisApiUrl;
    }

    public String getDfcfCb() {
        return dfcfCb;
    }

    public void setDfcfCb(String dfcfCb) {
        this.dfcfCb = dfcfCb;
    }

    public String getTushareApiUrl() {
        return tushareApiUrl;
    }

    public void setTushareApiUrl(String tushareApiUrl) {
        this.tushareApiUrl = tushareApiUrl;
    }

    public String getTushareToken() {
        return tushareToken;
    }

    public void setTushareToken(String tushareToken) {
        this.tushareToken = tushareToken;
    }



}
