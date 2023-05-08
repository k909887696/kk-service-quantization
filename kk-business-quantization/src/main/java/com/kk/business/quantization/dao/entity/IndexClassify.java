package com.kk.business.quantization.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 申万行业分类
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
@TableName("index_classify")
@ApiModel(value = "申万行业分类对象", description = "申万行业分类")
public class IndexClassify implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 指数代码
    */
    @ApiModelProperty("指数代码")
    @TableId("index_code")
    private String indexCode;

    /**
    * 行业名称
    */
    @ApiModelProperty("行业名称")
    @TableField("industry_name")
    private String industryName;

    /**
    * 父级代码
    */
    @ApiModelProperty("父级代码")
    @TableField("parent_code")
    private String parentCode;

    /**
    * 是否发布了指数
    */
    @ApiModelProperty("是否发布了指数")
    @TableField("is_pub")
    private String isPub;

    /**
    * 层级
    */
    @ApiModelProperty("层级")
    @TableField("level")
    private String level;

    /**
    * 行业代码
    */
    @ApiModelProperty("行业代码")
    @TableField("industry_code")
    private String industryCode;

    /**
    * 行业分类（SW申万）
    */
    @ApiModelProperty("行业分类（SW申万）")
    @TableField("src")
    private String src;

    public String getIndexCode() {
        return indexCode;
    }

    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode;
    }
    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }
    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
    public String getIsPub() {
        return isPub;
    }

    public void setIsPub(String isPub) {
        this.isPub = isPub;
    }
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }
    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return "IndexClassify{" +
            "indexCode=" + indexCode +
            ", industryName=" + industryName +
            ", parentCode=" + parentCode +
            ", isPub=" + isPub +
            ", level=" + level +
            ", industryCode=" + industryCode +
            ", src=" + src +
        "}";
    }
}
