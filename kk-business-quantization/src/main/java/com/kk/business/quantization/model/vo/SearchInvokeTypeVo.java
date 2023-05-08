package com.kk.business.quantization.model.vo;

import com.kk.common.base.model.BasePage;
import io.swagger.annotations.ApiModelProperty;

/**
 * 调度类型查询类
 */
public class SearchInvokeTypeVo extends BasePage {

    private static final long serialVersionUID = 1L;
    /**
     * 调度类型名称
     */
    @ApiModelProperty("调度类型名称")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
