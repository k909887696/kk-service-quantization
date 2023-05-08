package com.kk.business.quantization.model.tushare;

import java.util.List;

/**
 * @Author: kk
 * @Date: 2021/12/11 12:56
 */
public class TushareDataRes {


    /**
     * 字段信息
     */
    private List<String> fields;

    /**
     * 数据列表
     */
    private List<List<Object>> items;

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public List<List<Object>> getItems() {
        return items;
    }

    public void setItems(List<List<Object>> items) {
        this.items = items;
    }
}
