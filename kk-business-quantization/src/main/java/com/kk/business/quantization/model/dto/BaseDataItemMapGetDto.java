package com.kk.business.quantization.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Map;

public class BaseDataItemMapGetDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "返回处理结果")
    private boolean value;
    @Schema(description = "响应实体集合")
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