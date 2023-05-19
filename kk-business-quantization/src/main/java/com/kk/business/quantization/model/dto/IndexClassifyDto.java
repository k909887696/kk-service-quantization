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
 * 申万行业分类详情返回实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@ApiModel(value = "申万行业分类详情返回实体", description = "申万行业分类")
public class IndexClassifyDto implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 指数代码
    */
    @ApiModelProperty("指数代码")
    private String indexCode;
    /**
    * 行业名称
    */
    @ApiModelProperty("行业名称")
    private String industryName;
    /**
    * 父级代码
    */
    @ApiModelProperty("父级代码")
    private String parentCode;
    /**
    * 行业名称
    */
    @ApiModelProperty("行业名称")
    private String level;
    /**
    * 行业代码
    */
    @ApiModelProperty("行业代码")
    private String industryCode;
    /**
    * 	是否发布了指数
    */
    @ApiModelProperty("	是否发布了指数")
    private String isPub;
    /**
    * 行业分类（SW申万）
    */
    @ApiModelProperty("行业分类（SW申万）")
    private String src;


}
