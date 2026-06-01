package com.kk.business.quantization.model.po.dfcf;

/**
 * @Author: kk
 * @Date: 2021/12/11 12:55
 */
public class DfcfHisBaseRes {


    /**
     * 0 请求成功，非0都是异常 102：没有数据
     */
    private int rc;
    /**
     * 返回数据
     */
    private DfcfHisDataRes data;

    public DfcfHisDataRes getData() {
        return data;
    }

    public void setData(DfcfHisDataRes data) {
        this.data = data;
    }

    public int getRc() {
        return rc;
    }

    public void setRc(int rc) {
        this.rc = rc;
    }
}
