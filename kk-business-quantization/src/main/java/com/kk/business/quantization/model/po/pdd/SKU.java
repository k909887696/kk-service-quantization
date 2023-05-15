package com.kk.business.quantization.model.po.pdd;

import java.util.List;

public class SKU {


    private String sku_id;
    private String thumb_url;
    private Double price;
    private List<SKUSpec> spec;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getThumb_url() {
        return thumb_url;
    }

    public void setThumb_url(String thumb_url) {
        this.thumb_url = thumb_url;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<SKUSpec> getSpec() {
        return spec;
    }

    public void setSpec(List<SKUSpec> spec) {
        this.spec = spec;
    }
}
