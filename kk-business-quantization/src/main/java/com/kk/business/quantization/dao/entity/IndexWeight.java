package com.kk.business.quantization.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
/**
 * <p>
 * 指数成分权重
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@TableName("index_weight")
@ApiModel(value = "指数成分权重对象", description = "指数成分权重")
public class IndexWeight implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 指数代码
    */
    @ApiModelProperty("指数代码")
    @MppMultiId("index_code")
    private String indexCode;

    /**
    * 成分代码
    */
    @ApiModelProperty("成分代码")
    @MppMultiId("con_code")
    private String conCode;

    /**
    * 交易日期
    */
    @ApiModelProperty("交易日期")
    @TableField("trade_date")
    private String tradeDate;

    /**
    * 权重
    */
    @ApiModelProperty("权重")
    @TableField("weight")
    private Double weight;


}
