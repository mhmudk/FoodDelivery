package com.example.fooddelivery.pojo;

public class OrderProductItem {
    String productId = "";
    int quantity = 0;


    public OrderProductItem() {
    }

    public OrderProductItem(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;

    }


    public int getQuantity() {
        return quantity;
    }


}
