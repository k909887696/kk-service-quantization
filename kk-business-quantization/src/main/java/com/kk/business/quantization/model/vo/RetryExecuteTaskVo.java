package com.kk.business.quantization.model.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import com.kk.common.base.model.BasePage;
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
    @Schema(description = "任务编号")
    private String taskId;


    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
