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
 * 个人自选股列表查询实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@ApiModel(value = "个人自选股列表查询实体", description = "个人自选股")
public class SecuritySelectionListVo extends BasePage{

    private static final long serialVersionUID = 1L;

    private String tsCode;
    private String tradeDateStart;
    private String tradeDateEnd;

    private String selectType;


}
