package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.kk.common.base.model.BasePage;
/**
 * <p>
 * 系统设置-数据任务列表查询实体
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
@Data
@ApiModel(value = "系统设置-数据任务列表查询实体", description = "系统设置-数据任务")
public class CollectionTaskListVo extends BasePage{

    private static final long serialVersionUID = 1L;

    /**
    * 任务编号
    */
    @ApiModelProperty("任务编号")
    private String taskId;
    /**
    * 任务名称
    */
    @ApiModelProperty("任务名称")
    private String name;
    /**
    * 所属策略编号
    */
    @ApiModelProperty("所属策略编号")
    private String policyId;
    /**
    * 调用类编号
    */
    @ApiModelProperty("调用类编号")
    private String invokeCode;
    /**
    * 调用类方法名称
    */
    @ApiModelProperty("调用类方法名称")
    private String invokeMethod;
    /**
    * 调用类方法参数
    */
    @ApiModelProperty("调用类方法参数")
    private String invokeParams;
    /**
    * 预执行时间开始
    */
    @ApiModelProperty("预执行时间开始")
    private Date preRunTimeStart;
    /**
    * 预执行时间结束
    */
    @ApiModelProperty("预执行时间结束")
    private Date preRunTimeEnd;

    /**
    * 执行时间开始
    */
    @ApiModelProperty("执行时间开始")
    private Date runTimeStart;
    /**
    * 执行时间结束
    */
    @ApiModelProperty("执行时间结束")
    private Date runTimeEnd;

    /**
    * 完成时间开始
    */
    @ApiModelProperty("完成时间开始")
    private Date finishTimeStart;
    /**
    * 完成时间结束
    */
    @ApiModelProperty("完成时间结束")
    private Date finishTimeEnd;

    /**
    * 创建人编号
    */
    @ApiModelProperty("创建人编号")
    private String createId;
    /**
    * 创建人名称
    */
    @ApiModelProperty("创建人名称")
    private String createName;
    /**
    * 创建时间开始
    */
    @ApiModelProperty("创建时间开始")
    private Date createTimeStart;
    /**
    * 创建时间结束
    */
    @ApiModelProperty("创建时间结束")
    private Date createTimeEnd;

    /**
    * 执行次数
    */
    @ApiModelProperty("执行次数")
    private Integer runCount;
    /**
    * 异常信息
    */
    @ApiModelProperty("异常信息")
    private String exMsg;


}
