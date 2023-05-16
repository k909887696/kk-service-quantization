package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 申万行业明细	编辑实体
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
@Data
@ApiModel(value = "申万行业明细	编辑实体", description = "申万行业明细	")
public class IndexMemberEditVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String indexCode;
    private String indexName;
    private String conCode;
    private String conName;
    private String inDate;
    private String outDate;
    private String isNew;


}
