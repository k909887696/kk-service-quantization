package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.kk.common.base.model.BasePage;
/**
 * <p>
 * 个股kdj数据列表查询实体
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
@Data
@ApiModel(value = "个股kdj数据列表查询实体", description = "个股kdj数据")
public class StockDayKdjListVo extends BasePage{

    private static final long serialVersionUID = 1L;

    /**
    * 股票编号
    */
    @ApiModelProperty("股票编号")
    private String tsCode;
    /**
    * 交易日期开始
    */
    @ApiModelProperty("交易日期开始")
    private String tradeDateStart;
    /**
    * 交易日期结束
    */
    @ApiModelProperty("交易日期结束")
    private String tradeDateEnd;

    /**
    * k值
    */
    @ApiModelProperty("k值")
    private Double kValue;
    /**
    * d值
    */
    @ApiModelProperty("d值")
    private Double dValue;
    /**
    * j值
    */
    @ApiModelProperty("j值")
    private Double jValue;
    /**
    * rsv值
    */
    @ApiModelProperty("rsv值")
    private Double rsv;
    /**
    * kdj类型（默认 9_3_3）
    */
    @ApiModelProperty("kdj类型（默认 9_3_3）")
    private String kdjType;


}
