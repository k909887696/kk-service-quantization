package com.kk.business.quantization.model.po.tushare;

import com.kk.common.base.model.BasePage;

public class ConceptVo extends BasePage {

    /**
     * 市场类型A-a股 HK-港股 US-美股
     */
    private String exchange;

    /**
     * 指数类型 N-板块指数 I-行业指数 S-同花顺特色指数
     */
    private String type;

    /**
     * 来源，默认为ts
     */
    private String src;

    /**
     * 更新类型（cover：覆盖；add：叠加） 默认：add
     */
    private String updateType;

    public String getUpdateType() {
        return updateType;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
