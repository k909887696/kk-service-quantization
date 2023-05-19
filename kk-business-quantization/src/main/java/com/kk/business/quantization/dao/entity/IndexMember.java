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
 * 申万行业明细
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@TableName("index_member")
@ApiModel(value = "申万行业明细对象", description = "申万行业明细")
public class IndexMember implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 指数代码
    */
    @ApiModelProperty("指数代码")
    @MppMultiId("index_code")
    private String indexCode;

    /**
    * 指数名称
    */
    @ApiModelProperty("指数名称")
    @TableField("index_name")
    private String indexName;

    /**
    * 成分股票代码
    */
    @ApiModelProperty("成分股票代码")
    @MppMultiId("con_code")
    private String conCode;

    /**
    * 成分股票名称
    */
    @ApiModelProperty("成分股票名称")
    @TableField("con_name")
    private String conName;

    /**
    * 纳入日期
    */
    @ApiModelProperty("纳入日期")
    @TableField("in_date")
    private String inDate;

    /**
    * 	剔除日期
    */
    @ApiModelProperty("	剔除日期")
    @TableField("out_date")
    private String outDate;

    /**
    * 是否最新Y是N否
    */
    @ApiModelProperty("是否最新Y是N否")
    @TableField("is_new")
    private String isNew;


}
