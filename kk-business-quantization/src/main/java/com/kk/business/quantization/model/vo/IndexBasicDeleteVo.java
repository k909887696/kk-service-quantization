package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 指数基本信息删除实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@Schema(name = "指数基本信息删除实体", description = "指数基本信息")
public class IndexBasicDeleteVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * TS代码
    */
    @Schema(description = "TS代码")
    private String tsCode;


}
