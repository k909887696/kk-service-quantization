package com.kk.business.quantization.model.vobase.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.kk.common.base.model.BasePage;
/**
 * <p>
 * 人民币货币总量对象列表查询实体
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@Schema(description = "人民币货币总量对象列表查询实体")
public class CnMListReqVo extends BasePage{

    private static final long serialVersionUID = 1L;

    /**
    * 月份
    */
    @Schema(description = "月份")
    private String month;
    /**
     * 月份开始
     */
    @Schema(description = "月份开始")
    private String monthStart;
    /**
     * 月份结束
     */
    @Schema(description = "月份结束")
    private String monthEnd;

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
