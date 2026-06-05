package com.kk.business.quantization.model.vobase.res;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 各个市场涨跌幅限制详情返回实体
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@Schema( description = "各个市场涨跌幅限制详情返回实体")
public class MaxPctChgResVo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String market;
    private Double maxPctChg;


}
