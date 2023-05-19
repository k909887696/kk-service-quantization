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
 * 申万行业明细删除实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@ApiModel(value = "申万行业明细删除实体", description = "申万行业明细")
public class IndexMemberDeleteVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 指数代码
    */
    @ApiModelProperty("指数代码")
    private String indexCode;
    /**
    * 成分股票代码
    */
    @ApiModelProperty("成分股票代码")
    private String conCode;


}
