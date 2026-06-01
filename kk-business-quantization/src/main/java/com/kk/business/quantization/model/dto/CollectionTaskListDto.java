package com.kk.business.quantization.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 系统设置-数据任务列表返回实体
 * </p>
 *
 * @author kk
 * @since 2023-08-24
 */
@Data
@Schema(name = "系统设置-数据任务列表返回实体", description = "系统设置-数据任务")
public class CollectionTaskListDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 任务编号
    */
    @Schema(description = "任务编号")
    private String taskId;
    /**
    * 任务名称
    */
    @Schema(description = "任务名称")
    private String name;
    /**
    * 所属策略编号
    */
    @Schema(description = "所属策略编号")
    private String policyId;
    /**
    * 调用类编号
    */
    @Schema(description = "调用类编号")
    private String invokeCode;
    /**
    * 调用类方法名称
    */
    @Schema(description = "调用类方法名称")
    private String invokeMethod;
    /**
    * 调用类方法参数
    */
    @Schema(description = "调用类方法参数")
    private String invokeParams;
    /**
    * 预执行时间
    */
    @Schema(description = "预执行时间")
    private Date preRunTime;
    /**
    * 执行时间
    */
    @Schema(description = "执行时间")
    private Date runTime;
    /**
    * 完成时间
    */
    @Schema(description = "完成时间")
    private Date finishTime;
    /**
    * 创建人编号
    */
    @Schema(description = "创建人编号")
    private String createId;
    /**
    * 创建人名称
    */
    @Schema(description = "创建人名称")
    private String createName;
    /**
    * 创建时间
    */
    @Schema(description = "创建时间")
    private Date createTime;
    /**
    * 执行次数
    */
    @Schema(description = "执行次数")
    private Integer runCount;
    /**
    * 异常信息
    */
    @Schema(description = "异常信息")
    private String exMsg;
    /**
    * 调度渠道（公共渠道：1，其余值为自定义调度作业单独执行）
    */
    @Schema(description = "调度渠道（公共渠道：1，其余值为自定义调度作业单独执行）")
    private String channel;


}
