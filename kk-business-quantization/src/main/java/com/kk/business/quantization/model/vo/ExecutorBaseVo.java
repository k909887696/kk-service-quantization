package com.kk.business.quantization.model.vo;

import com.kk.common.base.model.BasePage;
import lombok.Data;

import java.util.List;

/**
 * 数据处理器基础参数
 * @author kk
 * @since 2023/8/21
 */
@Data
public class ExecutorBaseVo extends BasePage {

    /**
     * tscode列表
     */
    private List<String> ids;
    /**
     * 股票代码（支持多个股票同时提取，逗号分隔）
     */
    private  String tsCode;
    /**
     * 交易日期 20180506
     */
    private String tradeDate;
    /**
     * 交易日期（YYYYMMDD）
     */
    private String startDate;

    /**
     * 	结束日期(YYYYMMDD)
     */
    private String endDate;
}
