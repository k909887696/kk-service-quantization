package com.kk.business.quantization.model.tushare;

import com.kk.common.base.model.BasePage;
import io.swagger.models.properties.BaseIntegerProperty;

public class IndexClassifyVo extends BasePage {

    /**
     * 指数来源（SW2014：申万2014年版本，SW2021：申万2021年版本）
     */
    private String src;

    /**
     * 指数代码
     */
    private String indexCode;

    /**
     * 行业分级（L1/L2/L3）
     */
    private String level;
    /**
     * 父级代码（一级为0）
     */
    private String parentCode;

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

    public String getIndexCode() {
        return indexCode;
    }

    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}
