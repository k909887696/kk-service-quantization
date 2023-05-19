package com.kk.business.quantization.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * <p>
 * 指数基本信息
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@TableName("index_basic")
@ApiModel(value = "指数基本信息对象", description = "指数基本信息")
public class IndexBasic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * TS代码
    */
    @ApiModelProperty("TS代码")
    @TableId("ts_code")
    private String tsCode;

    /**
    * 简称
    */
    @ApiModelProperty("简称")
    @TableField("name")
    private String name;

    /**
    * 指数全称
    */
    @ApiModelProperty("指数全称")
    @TableField("fullname")
    private String fullname;

    /**
    * 市场
    */
    @ApiModelProperty("市场")
    @TableField("market")
    private String market;

    /**
    * 	发布方
    */
    @ApiModelProperty("	发布方")
    @TableField("publisher")
    private String publisher;

    /**
    * 指数风格
    */
    @ApiModelProperty("指数风格")
    @TableField("index_type")
    private String indexType;

    /**
    * 指数类别
    */
    @ApiModelProperty("指数类别")
    @TableField("category")
    private String category;

    /**
    * 基期
    */
    @ApiModelProperty("基期")
    @TableField("base_date")
    private String baseDate;

    /**
    * 基点
    */
    @ApiModelProperty("基点")
    @TableField("base_point")
    private Double basePoint;

    /**
    * 发布日期
    */
    @ApiModelProperty("发布日期")
    @TableField("list_date")
    private String listDate;

    /**
    * 加权方式
    */
    @ApiModelProperty("加权方式")
    @TableField("weight_rule")
    private String weightRule;

    /**
    * 描述
    */
    @ApiModelProperty("描述")
    @TableField("desc")
    private String desc;

    /**
    * 终止日期
    */
    @ApiModelProperty("终止日期")
    @TableField("exp_date")
    private String expDate;


}
