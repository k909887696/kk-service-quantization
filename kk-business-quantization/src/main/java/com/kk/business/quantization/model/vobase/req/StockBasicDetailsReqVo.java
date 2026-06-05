package com.kk.business.quantization.model.vobase.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 个股基本信息查询详情实体
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@Schema(description = "个股基本信息查询详情实体")
public class StockBasicDetailsReqVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * ts股票代码
    */
    @Schema(description = "ts股票代码")
    private String tsCode;


}
