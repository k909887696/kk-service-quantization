package com.kk.business.quantization.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 个股异常波动信息详情返回实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@ApiModel(value = "个股异常波动信息详情返回实体", description = "个股异常波动信息")
public class StockFluctuationDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private String code;
    private String tradeDate;
    private Integer maxUpCount;
    private Integer maxDownCount;
    private Integer maxUp15dCount;
    private Integer maxDown15dCount;


}
