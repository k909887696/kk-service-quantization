package com.kk.business.quantization.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
 import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 各个市场涨跌幅限制
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@TableName("max_pct_chg")
@Schema(description = "各个市场涨跌幅限制")
public class MaxPctChg implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("market")
    private String market;

    @TableField("max_pct_chg")
    private Double maxPctChg;


}
