package com.kk.business.quantization.model.dfcf;

import java.util.List;

public class DfcfData<T> {

    /**
     * tushare序列后的数据集
     */
    private List<T> data;
    /**
     * 总数量
     */
    private int total;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
