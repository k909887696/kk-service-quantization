package com.kk.business.quantization.model.res;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Map;

public class BaseDataItemMapGetRes implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("返回处理结果")
    private boolean value;
    @ApiModelProperty("响应实体集合")
    private Map<String, Map<String,Object>> queryTypeResult;
    public boolean isValue() {
        return value;
    }
    public void setValue(boolean value) {
        this.value = value;
    }
    public Map<String, Map<String, Object>> getQueryTypeResult() {
        return queryTypeResult;
    }
    public void setQueryTypeResult(Map<String, Map<String, Object>> queryTypeResult) {
        this.queryTypeResult = queryTypeResult;
    }
}