package com.kk.business.quantization.model.po.pdd;

import java.util.List;

public class GoodsList {

    private int total;
    private String sessionId;
    private List<Goods> goods_list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public List<Goods> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<Goods> goods_list) {
        this.goods_list = goods_list;
    }
}
