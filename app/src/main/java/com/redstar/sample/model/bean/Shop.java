package com.redstar.sample.model.bean;

/**
 * Created by cody.yi on 2016/8/4.
 */
public class Shop {

    private String shopName;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopName='" + shopName + '\'' +
                '}';
    }
}
