package com.kk.business.quantization.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
 import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
/**
 * <p>
 * 个人自选股
 * </p>
 *
 * @author kk
 * @since 2026-05-30
 */
@Data
@TableName("security_selection")
@Schema(description = "个人自选股")
public class SecuritySelection implements Serializable {

    private static final long serialVersionUID = 1L;

    @MppMultiId("ts_code")
    private String tsCode;

    @MppMultiId("trade_date")
    private String tradeDate;

    @TableField("select_type")
    private String selectType;


}
