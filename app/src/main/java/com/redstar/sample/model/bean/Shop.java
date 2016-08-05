package com.redstar.sample.model.bean;

/**
 * Created by cody.yi on 2016/8/4.
 */
public class Shop {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
                "id=" + id +
                ", shopName='" + shopName + '\'' +
                '}';
    }
}
