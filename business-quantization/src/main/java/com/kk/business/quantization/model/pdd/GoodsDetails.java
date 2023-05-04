package com.kk.business.quantization.model.pdd;

import java.util.List;

public class GoodsDetails {

    private long id;
    private String goods_name;
    private String goods_desc;
    private List<Gallery> carousel_gallery;
    private List<Gallery> detail_gallery;
    private List<String> cats;
private List<SKU> skus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_desc() {
        return goods_desc;
    }

    public void setGoods_desc(String goods_desc) {
        this.goods_desc = goods_desc;
    }

    public List<Gallery> getCarousel_gallery() {
        return carousel_gallery;
    }

    public void setCarousel_gallery(List<Gallery> carousel_gallery) {
        this.carousel_gallery = carousel_gallery;
    }

    public List<Gallery> getDetail_gallery() {
        return detail_gallery;
    }

    public void setDetail_gallery(List<Gallery> detail_gallery) {
        this.detail_gallery = detail_gallery;
    }

    public List<String> getCats() {
        return cats;
    }

    public void setCats(List<String> cats) {
        this.cats = cats;
    }

    public List<SKU> getSkus() {
        return skus;
    }

    public void setSkus(List<SKU> skus) {
        this.skus = skus;
    }
}
