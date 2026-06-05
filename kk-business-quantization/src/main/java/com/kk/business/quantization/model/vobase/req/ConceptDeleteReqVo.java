package com.kk.business.quantization.model.vobase.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 概念分类删除实体
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@Schema(description = "概念分类删除实体")
public class ConceptDeleteReqVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 概念代码
    */
    @Schema(description ="概念代码")
    private String code;


}
