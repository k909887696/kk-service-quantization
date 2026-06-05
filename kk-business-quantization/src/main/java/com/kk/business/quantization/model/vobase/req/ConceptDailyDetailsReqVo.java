package com.kk.business.quantization.model.vobase.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 概念日线行情查询详情实体
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@Schema(description = "概念日线行情查询详情实体")
public class ConceptDailyDetailsReqVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 概念编号
    */
    @Schema(description = "概念编号")
    private String conceptId;
    /**
    * 交易日期
    */
    @Schema(description = "交易日期")
    private String tradeDate;


}
