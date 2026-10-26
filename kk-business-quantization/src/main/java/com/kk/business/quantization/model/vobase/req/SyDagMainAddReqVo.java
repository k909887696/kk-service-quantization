package com.kk.business.quantization.model.vobase.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import com.kk.business.quantization.model.vobase.req.SyDagFlowAddReqVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * DAG主表新增实体
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Data
@Schema(description = "DAG主表")
public class SyDagMainAddReqVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * DAG编码
    */
    @Schema(description ="DAG编码")
    private String dagId;
    /**
    * DAG名称
    */
    @Schema(description ="DAG名称")
    private String dagName;
    /**
    * 关联开始DAG节点编号
    */
    @Schema(description ="关联开始DAG节点编号")
    private String nodeCode;
    /**
    * 执行节点默认参数
    */
    @Schema(description ="执行节点默认参数")
    private String runParams;
    /**
    * 执行cron表达式
    */
    @Schema(description ="执行cron表达式")
    private String runCron;
    /**
    * 预执行时间
    */
    @Schema(description ="预执行时间")
    private Date preRunTime;
    /**
    * 状态（1：启用，0：禁用）
    */
    @Schema(description ="状态（1：启用，0：禁用）")
    private String status;
    /**
    * 创建时间
    */
    @Schema(description ="创建时间")
    private Date createTime;
    /**
    * 异常信息
    */
    @Schema(description ="异常信息")
    private String exMessage;
    /**
    * 画布设计信息
    */
    @Schema(description ="画布设计信息")
    private String dagFlowInfo;
    /**
    * DAG流程数据列表
    */
    @Schema(description ="DAG流程数据列表")
    private List<SyDagFlowAddReqVo> dagFlowList;


}
