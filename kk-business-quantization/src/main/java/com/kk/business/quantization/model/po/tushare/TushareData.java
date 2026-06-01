package com.kk.business.quantization.model.po.tushare;

import java.util.List;

public class TushareData<T> {

    /**
     * tushare序列后的数据集
     */
    private List<T> data;
    /**
     * 是否还有更多的数据
     */
    private boolean hasMore;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }
}
