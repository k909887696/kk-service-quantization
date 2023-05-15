package com.kk.business.quantization.model.vo;

public class FileSliceInfoVo {
    private String hasdCode;
    private String fileName;
    private Integer size;
    private Integer shardIndex;
    private Integer shardSize;
    private Float shardTotal;

    public String getHasdCode() {
        return hasdCode;
    }

    public void setHasdCode(String hasdCode) {
        this.hasdCode = hasdCode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getShardIndex() {
        return shardIndex;
    }

    public void setShardIndex(Integer shardIndex) {
        this.shardIndex = shardIndex;
    }

    public Integer getShardSize() {
        return shardSize;
    }

    public void setShardSize(Integer shardSize) {
        this.shardSize = shardSize;
    }

    public Float getShardTotal() {
        return shardTotal;
    }

    public void setShardTotal(Float shardTotal) {
        this.shardTotal = shardTotal;
    }
}
