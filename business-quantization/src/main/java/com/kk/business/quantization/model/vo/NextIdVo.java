package com.kk.business.quantization.model.vo;

import io.swagger.annotations.ApiModelProperty;

public class NextIdVo {

    /**
     * 前缀
     */
    @ApiModelProperty("前缀")
    private String prefix;
    /**
     * 日期格式
     */
    @ApiModelProperty("日期格式")
    private String format;
    /**
     * 位数
     */
    @ApiModelProperty("位数")
    private int bit;
    /**
     * 个数
     */
    @ApiModelProperty("个数")
    private int size;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getBit() {
        return bit;
    }

    public void setBit(int bit) {
        this.bit = bit;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
