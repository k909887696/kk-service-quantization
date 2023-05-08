package com.kk.business.quantization.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
@TableName("security_selection")
@ApiModel(value = "对象", description = "")
public class SecuritySelection implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("ts_code")
    private String tsCode;

    @TableField("trade_date")
    private String tradeDate;

    @TableField("select_type")
    private String selectType;

    public String getTsCode() {
        return tsCode;
    }

    public void setTsCode(String tsCode) {
        this.tsCode = tsCode;
    }
    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }
    public String getSelectType() {
        return selectType;
    }

    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    @Override
    public String toString() {
        return "SecuritySelection{" +
            "tsCode=" + tsCode +
            ", tradeDate=" + tradeDate +
            ", selectType=" + selectType +
        "}";
    }
}
