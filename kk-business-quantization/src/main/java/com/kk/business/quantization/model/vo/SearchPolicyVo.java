package com.kk.business.quantization.model.vo;

import com.kk.common.base.model.BasePage;
import io.swagger.v3.oas.annotations.media.Schema;



/**
 * @Author: kk
 * @Date: 2021/12/24 15:22
 * 查询策略列表实体
 */
public class SearchPolicyVo   {

    private static final long serialVersionUID = 1L;

    /**
     * 策略编号
     */
    @Schema(description = "策略编号")
    private String policyId;

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }
}
