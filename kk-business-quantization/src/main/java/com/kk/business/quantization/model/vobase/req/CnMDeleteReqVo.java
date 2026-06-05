package com.kk.business.quantization.model.vobase.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 人民币货币总量对象删除实体
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@Schema(description = "人民币货币总量对象删除实体")
public class CnMDeleteReqVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 月份
    */
    @Schema(description ="月份")
    private String month;


}
