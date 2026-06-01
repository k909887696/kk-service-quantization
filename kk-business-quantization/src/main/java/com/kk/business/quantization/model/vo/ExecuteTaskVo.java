package com.kk.business.quantization.model.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class ExecuteTaskVo {

    /**
     * 任务策略编号集合
     */
    @Schema(description = "任务编号集合")
    private List<String> taskIds;

    public List<String> getTaskIds() {
        return taskIds;
    }

    public void setTaskIds(List<String> taskIds) {
        this.taskIds = taskIds;
    }
}
