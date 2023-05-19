package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 个股kdj数据新增实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@ApiModel(value = "个股kdj数据新增实体", description = "个股kdj数据")
public class StockDayKdjAddVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 股票编号
    */
    @ApiModelProperty("股票编号")
    private String tsCode;
    /**
    * 交易日期
    */
    @ApiModelProperty("交易日期")
    private String tradeDate;
    /**
    * k值
    */
    @ApiModelProperty("k值")
    private Double kValue;
    /**
    * d值
    */
    @ApiModelProperty("d值")
    private Double dValue;
    /**
    * j值
    */
    @ApiModelProperty("j值")
    private Double jValue;
    /**
    * rsv值
    */
    @ApiModelProperty("rsv值")
    private Double rsv;
    /**
    * kdj类型（默认 9_3_3）
    */
    @ApiModelProperty("kdj类型（默认 9_3_3）")
    private String kdjType;


}
