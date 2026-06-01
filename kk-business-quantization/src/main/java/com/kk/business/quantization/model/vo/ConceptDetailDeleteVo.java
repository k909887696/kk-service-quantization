package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 概念明细删除实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@Schema(name = "概念明细删除实体", description = "概念明细")
public class ConceptDetailDeleteVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 概念编号
    */
    @Schema(description = "概念编号")
    private String conceptId;
    /**
    * 股票编号
    */
    @Schema(description = "股票编号")
    private String tsCode;


}
