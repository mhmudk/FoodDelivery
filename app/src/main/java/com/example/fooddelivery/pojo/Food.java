package com.example.fooddelivery.pojo;

import java.io.Serializable;

public class Food implements Serializable {
    private String name, decsription = "", price, timeofdelivery = "", id;
    private String picUrl = "";
    private String category = "";

    public Food(String name, String decsription, String price, String timeofdelivery, String id, String picUrl, String category) {
        this.name = name;
        this.decsription = decsription;
        this.price = price;
        this.timeofdelivery = timeofdelivery;
        this.id = id;
        this.picUrl = picUrl;
        this.category = category;
    }

    public Food(String name, String decsription, String price, String timeofdelivery, String category) {
        this.name = name;
        this.decsription = decsription;
        this.price = price;
        this.timeofdelivery = timeofdelivery;
        this.category = category;

    }


    public Food() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getCategory() {
        return category;
    }


    public Food(String name, String price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getDecsription() {
        return decsription;
    }

    public String getPrice() {
        return price;
    }

}
