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
 * 市场基本信息查询详情实体
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@Schema(description = "市场基本信息查询详情实体")
public class MarketBasicDetailsReqVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 市场代码
    */
    @Schema(description = "市场代码")
    private String marketCode;


}
