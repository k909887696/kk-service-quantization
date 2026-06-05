package com.kk.business.quantization.model.vobase.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.kk.common.base.model.BasePage;
/**
 * <p>
 * 个股周线行情列表查询实体
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@Schema(description = "个股周线行情列表查询实体")
public class WeeklyListReqVo extends BasePage{

    private static final long serialVersionUID = 1L;

    private String tsCode;
    private String tradeDateStart;
    private String tradeDateEnd;

    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Double preClose;
    private Double change;
    private Double pctChg;
    private Double vol;
    private Double amount;


}
