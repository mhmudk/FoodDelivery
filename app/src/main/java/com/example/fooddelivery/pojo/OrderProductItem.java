package com.example.fooddelivery.pojo;

public class OrderProductItem {
    String productId = "";// رقم البرودكت
    int quantity = 0;//الكميله


    public OrderProductItem(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;

    }

    public OrderProductItem() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
