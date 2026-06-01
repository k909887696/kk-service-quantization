package com.kk.business.quantization.model.dto;

import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 人民币货币总量对象详情返回实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@Schema(name = "人民币货币总量对象详情返回实体", description = "人民币货币总量对象")
public class CnMDto implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 月份
    */
    @Schema(description = "月份")
    private String month;
    /**
    * 货币M0总量
    */
    @Schema(description = "货币M0总量")
    private Double m0;
    /**
    * 货币M0同比
    */
    @Schema(description = "货币M0同比")
    private Double m0Yoy;
    /**
    * 货币M0环比
    */
    @Schema(description = "货币M0环比")
    private Double m0Mom;
    /**
    * 货币M1总量
    */
    @Schema(description = "货币M1总量")
    private Double m1;
    /**
    * 货币M1同比
    */
    @Schema(description = "货币M1同比")
    private Double m1Yoy;
    /**
    * 货币M1环比
    */
    @Schema(description = "货币M1环比")
    private Double m1Mom;
    /**
    * 货币M2总量
    */
    @Schema(description = "货币M2总量")
    private Double m2;
    /**
    * 货币M2同比
    */
    @Schema(description = "货币M2同比")
    private Double m2Yoy;
    /**
    * 货币M2环比
    */
    @Schema(description = "货币M2环比")
    private Double m2Mom;


}
