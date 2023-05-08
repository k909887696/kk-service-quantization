package com.kk.business.quantization.model.vo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class BaseDataItemMapGetVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(
            "查询类型：InvokeCycleType：调度类型周期，"

    )
    @NotEmpty(message="查询类型不能为空")
    private List<String> queryTypeList;

    public List<String> getQueryTypeList() {
        return queryTypeList;
    }

    public void setQueryTypeList(List<String> queryTypeList) {
        this.queryTypeList = queryTypeList;
    }
}
