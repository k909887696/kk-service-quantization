package com.kk.business.quantization.model.vobase.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 市场基本信息编辑实体
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@Schema( description = "市场基本信息编辑实体")
public class MarketBasicEditReqVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 市场代码
    */
    @Schema(description = "市场代码")
    private String marketCode;
    /**
    * 市场名称
    */
    @Schema(description = "市场名称")
    private String marketName;
    /**
    * 更新时间
    */
    @Schema(description = "更新时间")
    private Date opDate;


}
