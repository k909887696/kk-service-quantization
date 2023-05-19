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
 * 概念分类查询详情实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@ApiModel(value = "概念分类查询详情实体", description = "概念分类")
public class ConceptDetailsVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 概念代码
    */
    @ApiModelProperty("概念代码")
    private String code;


}
