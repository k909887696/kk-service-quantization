package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.kk.common.base.model.BasePage;
/**
 * <p>
 * 各个市场涨跌幅限制列表查询实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@Schema(name = "各个市场涨跌幅限制列表查询实体", description = "各个市场涨跌幅限制")
public class MaxPctChgListVo extends BasePage{

    private static final long serialVersionUID = 1L;

    private String market;
    private Double maxPctChg;


}
