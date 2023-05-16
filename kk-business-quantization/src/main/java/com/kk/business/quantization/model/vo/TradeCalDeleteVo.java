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
 * 交易日历删除实体
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
@Data
@ApiModel(value = "交易日历删除实体", description = "交易日历")
public class TradeCalDeleteVo implements Serializable {

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


}
