package com.kk.business.quantization.model.tushare;

import com.kk.common.base.model.BasePage;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ConceptDetailVo extends BasePage {

    /**
     * 板块指数代码(同花顺)
     */
    private String tsCode;
    /**
     * 概念分类ID （id来自概念股分类接口）
     */
    private String id;
    /**
     * 概念分类ID （id来自概念股分类接口）编号列表
     */
    private List<String> ids;

    /**
     * 更新类型（cover：覆盖；add：叠加） 默认：add
     */
    private String updateType;

    /**
     * 来源，默认为ts,ths 同花顺，90 东方财富
     */
    private String src;

    public String getSrc() {
        if(StringUtils.isBlank(this.src))
            src="ts";
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getUpdateType() {
        return updateType;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public String getTsCode() {
        return tsCode;
    }

    public void setTsCode(String tsCode) {
        this.tsCode = tsCode;
    }
}
