package com.kk.business.quantization.model.po.tushare;

/**
 * @Author: kk
 * @Date: 2021/12/11 12:55
 */
public class TushareBaseRes {

    /**
     * 请求序列号
     */
    private String request_id;
    /**
     * 返回编码
     */
    private String code;
    /**
     * 返回提示信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private TushareDataRes data;

    /**
     * 是否还有更多需要处理
     */
    private boolean has_more;

    public boolean getHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public TushareDataRes getData() {
        return data;
    }

    public void setData(TushareDataRes data) {
        this.data = data;
    }
}
