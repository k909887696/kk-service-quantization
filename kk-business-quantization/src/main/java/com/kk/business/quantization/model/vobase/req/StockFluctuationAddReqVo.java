package com.kk.business.quantization.model.vobase.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 个股异常波动信息新增实体
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@Schema(description = "个股异常波动信息")
public class StockFluctuationAddReqVo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String code;
    private String tradeDate;
    private Integer maxUpCount;
    private Integer maxDownCount;
    private Integer maxUp15dCount;
    private Integer maxDown15dCount;


}
