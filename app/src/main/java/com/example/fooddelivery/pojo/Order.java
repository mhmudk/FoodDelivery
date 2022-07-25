package com.example.fooddelivery.pojo;

import androidx.annotation.Keep;

import com.example.fooddelivery.pojo.OrderProductItem;
import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
    String userUid = "";
    String orderId = "";
    String adress = "";
    String courierId = "";
    String statue = "Pending"; // Pending - Courier - Delivered
    int totalPrice = 0;
    @Exclude
    ArrayList<OrderProductItem> listProducts = new ArrayList<>();

    public Order() {
    }

    public Order(String userUid, String orderId, String adress, String courierId, String statue, int totalPrice, ArrayList<OrderProductItem> listProducts) {
        this.userUid = userUid;
        this.orderId = orderId;
        this.adress = adress;
        this.courierId = courierId;
        this.statue = statue;
        this.totalPrice = totalPrice;
        this.listProducts = listProducts;
    }
    public Order(String orderId, String adress, int totalPrice, String statue, ArrayList<OrderProductItem>listProducts) {
        this.orderId = orderId;
        this.adress = adress;
        this.totalPrice = totalPrice;
        this.statue = statue;
        this.listProducts = listProducts;
    }

    @Exclude
    public int getProductsCount() {
        return getListProducts().size();
    }
    @Exclude
    public int getQuantity() {
        int q = 0;
        for(OrderProductItem orderProductItem : getListProducts()) {
            q = q + orderProductItem.getQuantity();
        }

        return q;
    }
    public String getUserUid() {
        return userUid;
    }
    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }
    public String getOrderId() {
        return orderId;
    }
    public String getAdress() {
        return adress;
    }
    public void setAdress(String adress) {
        this.adress = adress;
    }
    public String getCourierId() {
        return courierId;
    }
    public int getTotalPrice() {
        return totalPrice;
    }
    public String getStatue() {
        return statue;
    }
    public ArrayList<OrderProductItem> getListProducts() {
        return listProducts;
    }
    public void setListProducts(ArrayList<OrderProductItem> listProducts) {
        this.listProducts = listProducts;
    }
}