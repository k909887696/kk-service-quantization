package com.kk.business.quantization.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author kk
 * @since 2023/8/21
 */
@Data
public class SelectMaxMinByDateRangeDto {

    /**
     * 区间最高收盘价
     */
    @ApiModelProperty("区间最高收盘价")
    private Double maxClose;
    /**
     * 区间最低收盘价
     */
    @ApiModelProperty("区间最低收盘价")
    private Double minClose;

    /**
     * 股票代码
     */
    @ApiModelProperty("股票代码")
    private String tsCode;
}
