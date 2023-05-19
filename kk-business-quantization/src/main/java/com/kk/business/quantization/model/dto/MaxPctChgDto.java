package com.kk.business.quantization.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 各个市场涨跌幅限制详情返回实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@ApiModel(value = "各个市场涨跌幅限制详情返回实体", description = "各个市场涨跌幅限制")
public class MaxPctChgDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private String market;
    private Double maxPctChg;


}
