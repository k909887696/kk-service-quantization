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
 * 申万行业明细列表返回实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@ApiModel(value = "申万行业明细列表返回实体", description = "申万行业明细")
public class IndexMemberListDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 指数代码
    */
    @ApiModelProperty("指数代码")
    private String indexCode;
    /**
    * 指数名称
    */
    @ApiModelProperty("指数名称")
    private String indexName;
    /**
    * 成分股票代码
    */
    @ApiModelProperty("成分股票代码")
    private String conCode;
    /**
    * 成分股票名称
    */
    @ApiModelProperty("成分股票名称")
    private String conName;
    /**
    * 纳入日期
    */
    @ApiModelProperty("纳入日期")
    private String inDate;
    /**
    * 	剔除日期
    */
    @ApiModelProperty("	剔除日期")
    private String outDate;
    /**
    * 是否最新Y是N否
    */
    @ApiModelProperty("是否最新Y是N否")
    private String isNew;


}
