package com.kk.business.quantization.model.vobase.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.kk.common.base.model.BasePage;
/**
 * <p>
 * 市场基本信息列表查询实体
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@Schema(description = "市场基本信息列表查询实体")
public class MarketBasicListReqVo extends BasePage{

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
    * 更新时间开始
    */
    @Schema(description ="更新时间开始")
    private Date opDateStart;
    /**
    * 更新时间结束
    */
    @Schema(description ="更新时间结束")
    private Date opDateEnd;



}
