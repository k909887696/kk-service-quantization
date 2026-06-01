package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 自定义主键序号编辑实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@Schema(name = "自定义主键序号编辑实体", description = "自定义主键序号")
public class SerialNoEditVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String serialName;
    private Integer nextValue;
    private Integer min;
    private String max;


}
