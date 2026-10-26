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
 * DAG执行节点查询详情实体
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Data
@Schema(description = "DAG执行节点查询详情实体")
public class SyDagNodeDetailsReqVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 节点编码
    */
    @Schema(description = "节点编码")
    private String nodeCode;


}
