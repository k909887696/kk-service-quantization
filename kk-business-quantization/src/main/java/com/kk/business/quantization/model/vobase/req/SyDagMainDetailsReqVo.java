package com.kk.business.quantization.model.vobase.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * DAG主表查询详情实体
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Data
@Schema(description = "DAG主表查询详情实体")
public class SyDagMainDetailsReqVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * DAG编码
    */
    @Schema(description = "DAG编码")
    private String dagId;


}
