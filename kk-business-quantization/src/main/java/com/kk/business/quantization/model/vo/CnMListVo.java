package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.kk.common.base.model.BasePage;
/**
 * <p>
 * 人民币货币总量对象列表查询实体
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
@Data
@ApiModel(value = "人民币货币总量对象列表查询实体", description = "人民币货币总量对象")
public class CnMListVo extends BasePage{

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
    /**
     * 月份
     */
    @ApiModelProperty("月份 格式（yyyyMM）")
    private String month;
    /**
    * 货币M0总量
    */
    @ApiModelProperty("货币M0总量")
    private Double m0;
    /**
    * 货币M0同比
    */
    @ApiModelProperty("货币M0同比")
    private Double m0Yoy;
    /**
    * 货币M0环比
    */
    @ApiModelProperty("货币M0环比")
    private Double m0Mom;
    /**
    * 货币M1总量
    */
    @ApiModelProperty("货币M1总量")
    private Double m1;
    /**
    * 货币M1同比
    */
    @ApiModelProperty("货币M1同比")
    private Double m1Yoy;
    /**
    * 货币M1环比
    */
    @ApiModelProperty("货币M1环比")
    private Double m1Mom;
    /**
    * 货币M2总量
    */
    @ApiModelProperty("货币M2总量")
    private Double m2;
    /**
    * 货币M2同比
    */
    @ApiModelProperty("货币M2同比")
    private Double m2Yoy;
    /**
    * 货币M2环比
    */
    @ApiModelProperty("货币M2环比")
    private Double m2Mom;


}
