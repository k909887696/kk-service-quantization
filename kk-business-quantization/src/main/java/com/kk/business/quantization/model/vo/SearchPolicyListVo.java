package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.kk.common.base.model.BasePage;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: kk
 * @Date: 2021/12/24 15:22
 * 查询策略列表实体
 */
public class SearchPolicyListVo extends BasePage {

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
     * 调用类编号
     */
    @ApiModelProperty("调用类编号")
    private String invokeCode;

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInvokeCode() {
        return invokeCode;
    }

    public void setInvokeCode(String invokeCode) {
        this.invokeCode = invokeCode;
    }
}
