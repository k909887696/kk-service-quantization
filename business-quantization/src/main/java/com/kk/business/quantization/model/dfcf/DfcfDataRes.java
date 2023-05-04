package com.kk.business.quantization.model.dfcf;

import com.kk.business.quantization.model.tushare.TushareDataRes;

import java.util.List;
import java.util.Map;

/**
 * @Author: kk
 * @Date: 2021/12/11 12:55
 */
public class DfcfDataRes {


    /**
     * 总数量
     */
    private int total;
    /**
     * 数据集合
     */
    private List<Map<String,Object>> diff;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Map<String, Object>> getDiff() {
        return diff;
    }

    public void setDiff(List<Map<String, Object>> diff) {
        this.diff = diff;
    }
}
