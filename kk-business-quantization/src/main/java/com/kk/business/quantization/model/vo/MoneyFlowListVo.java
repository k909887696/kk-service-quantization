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
 * 个股资金流向	列表查询实体
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
@Data
@ApiModel(value = "个股资金流向	列表查询实体", description = "个股资金流向	")
public class MoneyFlowListVo extends BasePage{

    private static final long serialVersionUID = 1L;

    private String tsCode;
    private String tradeDateStart;
    private String tradeDateEnd;

    private Integer buySmVol;
    private Double buySmAmount;
    private Integer sellSmVol;
    private Double sellSmAmount;
    private Integer buyMdVol;
    private Double buyMdAmount;
    private Integer sellMdVol;
    private Double sellMdAmount;
    private Integer buyLgVol;
    private Double buyLgAmount;
    private Integer sellLgVol;
    private Double sellLgAmount;
    private Integer buyElgVol;
    private Double buyElgAmount;
    private Integer sellElgVol;
    private Double sellElgAmount;
    private Integer netMfVol;
    private Double netMfAmount;


}
