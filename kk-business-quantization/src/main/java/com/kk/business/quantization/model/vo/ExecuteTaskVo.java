package com.kk.business.quantization.model.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class ExecuteTaskVo {

    /**
     * 任务策略编号集合
     */
    @ApiModelProperty("任务编号集合")
    private List<String> taskIds;

    public List<String> getTaskIds() {
        return taskIds;
    }

    public void setTaskIds(List<String> taskIds) {
        this.taskIds = taskIds;
    }
}
