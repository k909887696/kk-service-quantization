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
 * 指数成分权重列表查询实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@ApiModel(value = "指数成分权重列表查询实体", description = "指数成分权重")
public class IndexWeightListVo extends BasePage{

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
    * 交易日期开始
    */
    @ApiModelProperty("交易日期开始")
    private String tradeDateStart;
    /**
    * 交易日期结束
    */
    @ApiModelProperty("交易日期结束")
    private String tradeDateEnd;

    /**
    * 权重
    */
    @ApiModelProperty("权重")
    private Double weight;


}
