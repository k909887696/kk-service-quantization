package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 指数成分权重查询详情实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@Schema(name = "指数成分权重查询详情实体", description = "指数成分权重")
public class IndexWeightDetailsVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 指数代码
    */
    @Schema(description = "指数代码")
    private String indexCode;
    /**
    * 成分代码
    */
    @Schema(description = "成分代码")
    private String conCode;


}
