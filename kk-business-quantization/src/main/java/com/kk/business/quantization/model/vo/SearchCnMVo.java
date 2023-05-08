package com.kk.business.quantization.model.vo;

import com.kk.common.base.model.BasePage;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: kk
 * @Date: 2021/12/24 15:22
 * 查询人民币总量列表实体
 */
public class SearchCnMVo extends BasePage {

    private static final long serialVersionUID = 1L;

    /**
     * 月份开始 格式（yyyyMM）
     */
    @ApiModelProperty("月份开始 格式（yyyyMM）")
    private String monthStart;

    /**
     * 月份结束 格式（yyyyMM）
     */
    @ApiModelProperty("月份结束 格式（yyyyMM）")
    private String monthEnd;

    public String getMonthStart() {
        return monthStart;
    }

    public void setMonthStart(String monthStart) {
        this.monthStart = monthStart;
    }

    public String getMonthEnd() {
        return monthEnd;
    }

    public void setMonthEnd(String monthEnd) {
        this.monthEnd = monthEnd;
    }
}
