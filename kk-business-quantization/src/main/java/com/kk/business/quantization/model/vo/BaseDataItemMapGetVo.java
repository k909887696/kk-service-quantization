package com.kk.business.quantization.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class BaseDataItemMapGetVo {

    private static final long serialVersionUID = 1L;

    @Schema(description = "查询类型：InvokeCycleType：调度类型周期，")
    private List<String> queryTypeList;

    public List<String> getQueryTypeList() {
        return queryTypeList;
    }

    public void setQueryTypeList(List<String> queryTypeList) {
        this.queryTypeList = queryTypeList;
    }
}
