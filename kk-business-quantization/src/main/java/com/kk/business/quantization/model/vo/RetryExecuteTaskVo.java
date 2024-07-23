package com.kk.business.quantization.model.vo;

import com.kk.common.base.model.BasePage;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: kk
 * @Date: 2021/12/24 15:22
 * 查询策略列表实体
 */
public class RetryExecuteTaskVo  {

    private static final long serialVersionUID = 1L;

    /**
     * 任务编号
     */
    @ApiModelProperty("任务编号")
    private String taskId;


    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
