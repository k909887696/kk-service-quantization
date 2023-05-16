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
 * 概念明细删除实体
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
@Data
@ApiModel(value = "概念明细删除实体", description = "概念明细")
public class ConceptDetailDeleteVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 概念编号
    */
    @ApiModelProperty("概念编号")
    private String conceptId;
    /**
    * 股票编号
    */
    @ApiModelProperty("股票编号")
    private String tsCode;


}
