package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 人民币货币总量对象查询详情实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@Schema(name = "人民币货币总量对象查询详情实体", description = "人民币货币总量对象")
public class CnMDetailsVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 月份
    */
    @Schema(description = "月份")
    private String month;


}
