package com.kk.business.quantization.model.vobase.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 指数日线行情删除实体
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@Schema(description = "指数日线行情删除实体")
public class IndexDailyDeleteReqVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * TS指数代码
    */
    @Schema(description ="TS指数代码")
    private String tsCode;
    /**
    * 交易日期
    */
    @Schema(description ="交易日期")
    private String tradeDate;


}
