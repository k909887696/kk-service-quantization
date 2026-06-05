package com.kk.business.quantization.model.vobase.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.kk.common.base.model.BasePage;
/**
 * <p>
 * 个股异常波动信息列表查询实体
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@Schema(description = "个股异常波动信息列表查询实体")
public class StockFluctuationListReqVo extends BasePage{

    private static final long serialVersionUID = 1L;

    private String code;
    private String tradeDateStart;
    private String tradeDateEnd;

    private Integer maxUpCount;
    private Integer maxDownCount;
    private Integer maxUp15dCount;
    private Integer maxDown15dCount;


}
