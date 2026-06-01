package com.kk.business.quantization.model.vobase.res;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 个人自选股详情返回实体
 * </p>
 *
 * @author kk
 * @since 2026-05-30
 */
@Data
@Schema( description = "个人自选股详情返回实体")
public class SecuritySelectionResVo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String tsCode;
    private String tradeDate;
    private String selectType;


}
