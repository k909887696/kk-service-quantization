package com.kk.business.quantization.model.vobase.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 各个市场涨跌幅限制查询详情实体
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@Schema(description = "各个市场涨跌幅限制查询详情实体")
public class MaxPctChgDetailsReqVo implements Serializable {

    private static final long serialVersionUID = 1L;


}
