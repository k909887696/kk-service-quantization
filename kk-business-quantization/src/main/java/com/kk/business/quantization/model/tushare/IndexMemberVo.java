package com.kk.business.quantization.model.tushare;

import com.kk.common.base.model.BasePage;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class IndexMemberVo extends BasePage {


    /**
     * 行业编号列表
     */
    private List<String> ids;

    /**
     * 指数代码
     */
    private String indexCode;

    /**
     * 股票代码
     */
    private String tsCode;
    /**
     * 	是否最新（默认为“Y是”）
     */
    private String isNew;

    /**
     * 	指数来源（SW2014：申万2014年版本，SW2021：申万2021年版本）
     */
    private String src;

    public String getSrc() {
        if(StringUtils.isBlank(this.src))
            src="SW2021";
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

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

    public String getIndexCode() {
        return indexCode;
    }

    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode;
    }

    public String getTsCode() {
        return tsCode;
    }

    public void setTsCode(String tsCode) {
        this.tsCode = tsCode;
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
