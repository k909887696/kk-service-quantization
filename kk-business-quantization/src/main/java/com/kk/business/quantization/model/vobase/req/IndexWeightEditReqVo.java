package com.kk.business.quantization.model.vobase.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 指数成分权重编辑实体
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@Schema( description = "指数成分权重编辑实体")
public class IndexWeightEditReqVo implements Serializable {

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
    /**
    * 交易日期
    */
    @Schema(description = "交易日期")
    private String tradeDate;
    /**
    * 权重
    */
    @Schema(description = "权重")
    private Double weight;


}
