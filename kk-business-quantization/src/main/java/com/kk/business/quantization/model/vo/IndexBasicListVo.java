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
 * 指数基本信息列表查询实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@ApiModel(value = "指数基本信息列表查询实体", description = "指数基本信息")
public class IndexBasicListVo extends BasePage{

    private static final long serialVersionUID = 1L;

    /**
    * TS代码
    */
    @ApiModelProperty("TS代码")
    private String tsCode;
    /**
    * 简称
    */
    @ApiModelProperty("简称")
    private String name;
    /**
    * 指数全称
    */
    @ApiModelProperty("指数全称")
    private String fullname;
    /**
    * 市场
    */
    @ApiModelProperty("市场")
    private String market;
    /**
    * 	发布方
    */
    @ApiModelProperty("	发布方")
    private String publisher;
    /**
    * 指数风格
    */
    @ApiModelProperty("指数风格")
    private String indexType;
    /**
    * 指数类别
    */
    @ApiModelProperty("指数类别")
    private String category;
    /**
    * 基期开始
    */
    @ApiModelProperty("基期开始")
    private String baseDateStart;
    /**
    * 基期结束
    */
    @ApiModelProperty("基期结束")
    private String baseDateEnd;

    /**
    * 基点
    */
    @ApiModelProperty("基点")
    private Double basePoint;
    /**
    * 发布日期开始
    */
    @ApiModelProperty("发布日期开始")
    private String listDateStart;
    /**
    * 发布日期结束
    */
    @ApiModelProperty("发布日期结束")
    private String listDateEnd;

    /**
    * 加权方式
    */
    @ApiModelProperty("加权方式")
    private String weightRule;
    /**
    * 描述
    */
    @ApiModelProperty("描述")
    private String desc;
    /**
    * 终止日期开始
    */
    @ApiModelProperty("终止日期开始")
    private String expDateStart;
    /**
    * 终止日期结束
    */
    @ApiModelProperty("终止日期结束")
    private String expDateEnd;



}
