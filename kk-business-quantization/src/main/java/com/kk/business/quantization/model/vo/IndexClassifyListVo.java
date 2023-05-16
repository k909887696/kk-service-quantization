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
 * 申万行业分类	列表查询实体
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
@Data
@ApiModel(value = "申万行业分类	列表查询实体", description = "申万行业分类	")
public class IndexClassifyListVo extends BasePage{

    private static final long serialVersionUID = 1L;

    private String indexCode;
    private String industryName;
    private String level;
    private String industryCode;
    private String src;


}
