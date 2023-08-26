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
 * 系统设置-数据策略列表查询实体
 * </p>
 *
 * @author kk
 * @since 2023-08-24
 */
@Data
@ApiModel(value = "系统设置-数据策略列表查询实体", description = "系统设置-数据策略")
public class CollectionPolicyListVo extends BasePage{

    private static final long serialVersionUID = 1L;

    /**
    * 策略编号
    */
    @ApiModelProperty("策略编号")
    private String policyId;
    /**
    * 策略名称
    */
    @ApiModelProperty("策略名称")
    private String name;
    /**
    * 调度类型编号
    */
    @ApiModelProperty("调度类型编号")
    private String invokeMethod;
    /**
    * 调度类型方法
    */
    @ApiModelProperty("调度类型方法")
    private String invokeCode;
    /**
    * 执行周期（min：每几分钟，hour：每几小时，day:每天，week：每周，month：每月，year：每年）
    */
    @ApiModelProperty("执行周期（min：每几分钟，hour：每几小时，day:每天，week：每周，month：每月，year：每年）")
    private String invokeCycle;
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
    * 调度类型参数
    */
    @ApiModelProperty("调度类型参数")
    private String invokeParams;
    /**
    * 执行次数（超过10次自动挂起）
    */
    @ApiModelProperty("执行次数（超过10次自动挂起）")
    private Integer runCount;
    /**
    * 异常信息
    */
    @ApiModelProperty("异常信息")
    private String exMsg;
    /**
    * 执行周期大小
    */
    @ApiModelProperty("执行周期大小")
    private String invokeCycleTime;
    /**
    * 调度渠道（公共渠道：1，其余值为自定义调度作业单独执行）
    */
    @ApiModelProperty("调度渠道（公共渠道：1，其余值为自定义调度作业单独执行）")
    private String channel;


}
