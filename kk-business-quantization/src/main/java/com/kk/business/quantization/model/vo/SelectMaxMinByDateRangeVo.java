package com.kk.business.quantization.model.vo;

import lombok.Data;

/**
 * @author kk
 * @since 2023/8/21
 */
@Data
public class SelectMaxMinByDateRangeVo {

    /**
     * 交易日期（YYYYMMDD）
     */
    private String startDate;

    /**
     * 	结束日期(YYYYMMDD)
     */
    private String endDate;
}
