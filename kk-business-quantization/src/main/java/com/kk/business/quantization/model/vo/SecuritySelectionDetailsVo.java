package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 个人自选股查询详情实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@Schema(name = "个人自选股查询详情实体", description = "个人自选股")
public class SecuritySelectionDetailsVo implements Serializable {

    private static final long serialVersionUID = 1L;


}
