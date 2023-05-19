package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 个股周线行情新增实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@ApiModel(value = "个股周线行情新增实体", description = "个股周线行情")
public class WeeklyAddVo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String tsCode;
    private String tradeDate;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Double preClose;
    private Double change;
    private Double pctChg;
    private Double vol;
    private Double amount;


}
