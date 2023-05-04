package com.kk.business.quantization.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
@TableName("index_member")
@ApiModel(value = "对象", description = "")
public class IndexMember implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 指数代码
    */
    @ApiModelProperty("指数代码")
    @MppMultiId("index_code")
    private String indexCode;

    /**
    * 指数名称
    */
    @ApiModelProperty("指数名称")
    @TableField("index_name")
    private String indexName;

    /**
    * 成分股票代码
    */
    @ApiModelProperty("成分股票代码")
    @MppMultiId("con_code")
    private String conCode;

    /**
    * 成分股票名称
    */
    @ApiModelProperty("成分股票名称")
    @TableField("con_name")
    private String conName;

    /**
    * 纳入日期
    */
    @ApiModelProperty("纳入日期")
    @TableField("in_date")
    private String inDate;

    /**
    * 剔除日期
    */
    @ApiModelProperty("剔除日期")
    @TableField("out_date")
    private String outDate;

    /**
    * 是否最新Y是N否
    */
    @ApiModelProperty("是否最新Y是N否")
    @TableField("is_new")
    private String isNew;

    public String getIndexCode() {
        return indexCode;
    }

    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode;
    }
    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }
    public String getConCode() {
        return conCode;
    }

    public void setConCode(String conCode) {
        this.conCode = conCode;
    }
    public String getConName() {
        return conName;
    }

    public void setConName(String conName) {
        this.conName = conName;
    }
    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }
    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }
    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    @Override
    public String toString() {
        return "IndexMember{" +
            "indexCode=" + indexCode +
            ", indexName=" + indexName +
            ", conCode=" + conCode +
            ", conName=" + conName +
            ", inDate=" + inDate +
            ", outDate=" + outDate +
            ", isNew=" + isNew +
        "}";
    }
}
