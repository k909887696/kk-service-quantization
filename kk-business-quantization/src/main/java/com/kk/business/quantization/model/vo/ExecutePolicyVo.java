package com.kk.business.quantization.model.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class ExecutePolicyVo {

    /**
     * 任务策略编号集合
     */
    @ApiModelProperty("任务策略编号集合")
    private List<String> policyIds;

    public List<String> getPolicyIds() {
        return policyIds;
    }

    public void setPolicyIds(List<String> policyIds) {
        this.policyIds = policyIds;
    }
}
