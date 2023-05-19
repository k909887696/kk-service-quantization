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
 * 申万行业分类
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@TableName("index_classify")
@ApiModel(value = "申万行业分类对象", description = "申万行业分类")
public class IndexClassify implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 指数代码
    */
    @ApiModelProperty("指数代码")
    @TableId("index_code")
    private String indexCode;

    /**
    * 行业名称
    */
    @ApiModelProperty("行业名称")
    @TableField("industry_name")
    private String industryName;

    /**
    * 父级代码
    */
    @ApiModelProperty("父级代码")
    @TableField("parent_code")
    private String parentCode;

    /**
    * 行业名称
    */
    @ApiModelProperty("行业名称")
    @TableField("level")
    private String level;

    /**
    * 行业代码
    */
    @ApiModelProperty("行业代码")
    @TableField("industry_code")
    private String industryCode;

    /**
    * 	是否发布了指数
    */
    @ApiModelProperty("	是否发布了指数")
    @TableField("is_pub")
    private String isPub;

    /**
    * 行业分类（SW申万）
    */
    @ApiModelProperty("行业分类（SW申万）")
    @TableField("src")
    private String src;


}
