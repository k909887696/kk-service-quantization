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
 * 个股异常波动信息列表查询实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@ApiModel(value = "个股异常波动信息列表查询实体", description = "个股异常波动信息")
public class StockFluctuationListVo extends BasePage{

    private static final long serialVersionUID = 1L;

    private String code;
    private String tradeDateStart;
    private String tradeDateEnd;

    private Integer maxUpCount;
    private Integer maxDownCount;
    private Integer maxUp15dCount;
    private Integer maxDown15dCount;


}
