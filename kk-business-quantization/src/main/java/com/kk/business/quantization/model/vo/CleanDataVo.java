package com.kk.business.quantization.model.vo;

/**
 * @Author: kk
 * @Date: 2021/12/29 13:53
 */
public class CleanDataVo {
    /**
     * 清理日期（YYYYMMDD）
     */
    private String cleanDate;

    /**
     * 保留多少天数据 默认3天
     */
    private int holdDataDay;

    public int getHoldDataDay() {
        if(holdDataDay<=0)
            holdDataDay=3;//默认3天
        return holdDataDay;
    }

    public void setHoldDataDay(int holdDataDay) {
        this.holdDataDay = holdDataDay;
    }

    public String getCleanDate() {
        return cleanDate;
    }

    public void setCleanDate(String cleanDate) {
        this.cleanDate = cleanDate;
    }
}
