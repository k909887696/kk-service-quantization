package com.kk.business.quantization.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 指数成分权重详情返回实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@ApiModel(value = "指数成分权重详情返回实体", description = "指数成分权重")
public class IndexWeightDto implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 指数代码
    */
    @ApiModelProperty("指数代码")
    private String indexCode;
    /**
    * 成分代码
    */
    @ApiModelProperty("成分代码")
    private String conCode;
    /**
    * 交易日期
    */
    @ApiModelProperty("交易日期")
    private String tradeDate;
    /**
    * 权重
    */
    @ApiModelProperty("权重")
    private Double weight;


}
