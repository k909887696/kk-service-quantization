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
 * 个股日线行情查询详情实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@ApiModel(value = "个股日线行情查询详情实体", description = "个股日线行情")
public class DailyDetailsVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 股票代码
    */
    @ApiModelProperty("股票代码")
    private String tsCode;
    /**
    * 交易日期
    */
    @ApiModelProperty("交易日期")
    private String tradeDate;


}
