package com.kk.business.quantization.model.vobase.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 个股月线行情编辑实体
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@Schema( description = "个股月线行情编辑实体")
public class MonthlyEditReqVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tsCode;
    private String tradeDate;
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
