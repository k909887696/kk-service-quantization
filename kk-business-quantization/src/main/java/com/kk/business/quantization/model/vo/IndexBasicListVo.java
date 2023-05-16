package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.kk.common.base.model.BasePage;
/**
 * <p>
 * 指数基本信息	列表查询实体
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
@Data
@ApiModel(value = "指数基本信息	列表查询实体", description = "指数基本信息	")
public class IndexBasicListVo extends BasePage{

    private static final long serialVersionUID = 1L;

    private String tsCode;
    private String name;
    private String fullname;
    private String market;
    private String publisher;
    private String indexType;
    private String category;
    private String baseDateStart;
    private String baseDateEnd;

    private Double basePoint;
    private String listDateStart;
    private String listDateEnd;

    private String weightRule;
    private String desc;
    private String expDateStart;
    private String expDateEnd;



}
