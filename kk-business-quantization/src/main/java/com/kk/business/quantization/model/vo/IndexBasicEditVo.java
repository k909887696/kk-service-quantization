package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 指数基本信息	编辑实体
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
@Data
@ApiModel(value = "指数基本信息	编辑实体", description = "指数基本信息	")
public class IndexBasicEditVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tsCode;
    private String name;
    private String fullname;
    private String market;
    private String publisher;
    private String indexType;
    private String category;
    private String baseDate;
    private Double basePoint;
    private String listDate;
    private String weightRule;
    private String desc;
    private String expDate;


}
