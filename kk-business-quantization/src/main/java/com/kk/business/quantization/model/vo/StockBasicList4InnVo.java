package com.kk.business.quantization.model.vo;

import com.kk.common.base.model.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 个股基本信息列表查询实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@ApiModel(value = "个股基本信息列表查询实体", description = "个股基本信息")
public class StockBasicList4InnVo extends BasePage{

    private static final long serialVersionUID = 1L;


    /**
    * 股票代码
    */
    @ApiModelProperty("股票代码")
    private List<String> symbolList;


}
