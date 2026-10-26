package com.kk.business.quantization.model.vobase.res;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * DAG执行流详情返回实体
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Data
@Schema( description = "DAG执行流详情返回实体")
public class SyDagFlowResVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 节点编码
    */
    @Schema(description = "节点编码")
    private String nodeCode;
    /**
    * 下级节点编码
    */
    @Schema(description = "下级节点编码")
    private String nextNodeCode;
    /**
    * 上级节点编码
    */
    @Schema(description = "上级节点编码")
    private String preNodeCode;
    /**
    * DAG主表ID
    */
    @Schema(description = "DAG主表ID")
    private String dagId;
    /**
    * DAG流程明细ID
    */
    @Schema(description = "DAG流程明细ID")
    private String dagFlowId;
    /**
    * 画布信息JSON
    */
    @Schema(description = "画布信息JSON")
    private String flowInfo;


}
