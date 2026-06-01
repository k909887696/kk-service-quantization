package com.kk.business.quantization.model.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import com.kk.common.base.model.BasePage;
/**
 * 调度类型查询类
 */
public class SearchInvokeTypeVo extends BasePage {

    private static final long serialVersionUID = 1L;
    /**
     * 调度类型名称
     */
    @Schema(description = "调度类型名称")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
