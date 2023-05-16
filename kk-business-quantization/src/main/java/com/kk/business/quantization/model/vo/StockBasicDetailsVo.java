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
 * 个股基本信息查询详情实体
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
@Data
@ApiModel(value = "个股基本信息查询详情实体", description = "个股基本信息")
public class StockBasicDetailsVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * ts股票代码
    */
    @ApiModelProperty("ts股票代码")
    private String tsCode;


}
