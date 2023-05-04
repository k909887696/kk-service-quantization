package com.kk.business.quantization.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 人民币货币总量
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
@TableName("cn_m")
@ApiModel(value = "人民币货币总量对象", description = "人民币货币总量")
public class CnM implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 月份
    */
    @ApiModelProperty("月份")
    @TableId("month")
    private String month;

    /**
    * 货币M0总量
    */
    @ApiModelProperty("货币M0总量")
    @TableField("m0")
    private Double m0;

    /**
    * 货币M0同比
    */
    @ApiModelProperty("货币M0同比")
    @TableField("m0_yoy")
    private Double m0Yoy;

    /**
    * 货币M0环比
    */
    @ApiModelProperty("货币M0环比")
    @TableField("m0_mom")
    private Double m0Mom;

    /**
    * 货币M1总量
    */
    @ApiModelProperty("货币M1总量")
    @TableField("m1")
    private Double m1;

    /**
    * 货币M1同比
    */
    @ApiModelProperty("货币M1同比")
    @TableField("m1_yoy")
    private Double m1Yoy;

    /**
    * 货币M1环比
    */
    @ApiModelProperty("货币M1环比")
    @TableField("m1_mom")
    private Double m1Mom;

    /**
    * 货币M2总量
    */
    @ApiModelProperty("货币M2总量")
    @TableField("m2")
    private Double m2;

    /**
    * 货币M2同比
    */
    @ApiModelProperty("货币M2同比")
    @TableField("m2_yoy")
    private Double m2Yoy;

    /**
    * 货币M2环比
    */
    @ApiModelProperty("货币M2环比")
    @TableField("m2_mom")
    private Double m2Mom;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
    public Double getm0() {
        return m0;
    }

    public void setm0(Double m0) {
        this.m0 = m0;
    }
    public Double getm0Yoy() {
        return m0Yoy;
    }

    public void setm0Yoy(Double m0Yoy) {
        this.m0Yoy = m0Yoy;
    }
    public Double getm0Mom() {
        return m0Mom;
    }

    public void setm0Mom(Double m0Mom) {
        this.m0Mom = m0Mom;
    }
    public Double getm1() {
        return m1;
    }

    public void setm1(Double m1) {
        this.m1 = m1;
    }
    public Double getm1Yoy() {
        return m1Yoy;
    }

    public void setm1Yoy(Double m1Yoy) {
        this.m1Yoy = m1Yoy;
    }
    public Double getm1Mom() {
        return m1Mom;
    }

    public void setm1Mom(Double m1Mom) {
        this.m1Mom = m1Mom;
    }
    public Double getm2() {
        return m2;
    }

    public void setm2(Double m2) {
        this.m2 = m2;
    }
    public Double getm2Yoy() {
        return m2Yoy;
    }

    public void setm2Yoy(Double m2Yoy) {
        this.m2Yoy = m2Yoy;
    }
    public Double getm2Mom() {
        return m2Mom;
    }

    public void setm2Mom(Double m2Mom) {
        this.m2Mom = m2Mom;
    }

    @Override
    public String toString() {
        return "CnM{" +
            "month=" + month +
            ", m0=" + m0 +
            ", m0Yoy=" + m0Yoy +
            ", m0Mom=" + m0Mom +
            ", m1=" + m1 +
            ", m1Yoy=" + m1Yoy +
            ", m1Mom=" + m1Mom +
            ", m2=" + m2 +
            ", m2Yoy=" + m2Yoy +
            ", m2Mom=" + m2Mom +
        "}";
    }
}
