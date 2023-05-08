package com.kk.business.quantization.model.vo;

import com.kk.common.base.model.BasePage;

import java.util.List;

/**
 * @Author: kk
 * @Date: 2021/12/11 11:37
 *  获取行情接口请求参数
 */
public class SearchDailyLeaderVo extends BasePage {




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


}
