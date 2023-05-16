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
 * 交易日历新增实体
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
@Data
@ApiModel(value = "交易日历新增实体", description = "交易日历")
public class TradeCalAddVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 交易所 SSE上交所 SZSE深交所
    */
    @ApiModelProperty("交易所 SSE上交所 SZSE深交所")
    private String exchange;
    /**
    * 日历日期
    */
    @ApiModelProperty("日历日期")
    private String calDate;
    /**
    * 是否交易 0休市 1交易
    */
    @ApiModelProperty("是否交易 0休市 1交易")
    private String isOpen;
    /**
    * 上一个交易日
    */
    @ApiModelProperty("上一个交易日")
    private String pretradeDate;


}
