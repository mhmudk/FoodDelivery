package com.example.fooddelivery.pojo;

import java.io.Serializable;

public class CartItem implements Serializable {
    private String id ;
    private int quantity = 1 ;

    public CartItem(String id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }



    public CartItem(String id) {
        this.id = id;
    }

    public CartItem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

}
