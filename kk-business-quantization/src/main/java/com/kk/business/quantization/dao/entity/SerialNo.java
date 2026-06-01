package com.kk.business.quantization.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

/**
 * <p>
 * 自定义主键序号
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
@TableName("serial_no")
@Schema(name = "自定义主键序号对象", description = "自定义主键序号")
public class SerialNo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 自定义主键id前缀
    */
    @Schema(description = "自定义主键id前缀")
    @TableId("serial_Name")
    private String serialName;

    /**
    * 下一个值
    */
    @Schema(description = "下一个值")
    @TableField("next_value")
    private Long nextValue;

    /**
    * 最小值
    */
    @Schema(description = "最小值")
    @TableField("min")
    private Integer min;

    /**
    * 最大值
    */
    @Schema(description = "最大值")
    @TableField("max")
    private Integer max;

    public String getSerialName() {
        return serialName;
    }

    public void setSerialName(String serialName) {
        this.serialName = serialName;
    }
    public Long getNextValue() {
        return nextValue;
    }

    public void setNextValue(Long nextValue) {
        this.nextValue = nextValue;
    }
    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }
    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "SerialNo{" +
            "serialName=" + serialName +
            ", nextValue=" + nextValue +
            ", min=" + min +
            ", max=" + max +
        "}";
    }
}
