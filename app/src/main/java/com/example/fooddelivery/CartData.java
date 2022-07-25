package com.example.fooddelivery;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.fooddelivery.pojo.CartItem;
import com.example.fooddelivery.pojo.Food;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CartData {

    SharedPreferences sharedPreferences;
    Context mContext;
    String ITEM_KEY = "cartquantity";

    public CartData(Context mContext) {
        this.mContext = mContext;
        sharedPreferences = mContext.getSharedPreferences(mContext.getPackageName(), MODE_PRIVATE);
    }

    public ArrayList<CartItem> getCart() {
        ArrayList<CartItem> arrayList;
        Gson gson = new Gson();
        String json = sharedPreferences.getString(ITEM_KEY, null);
        Type type = new TypeToken<ArrayList<CartItem>>() {
        }.getType();
        arrayList = gson.fromJson(json, type);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }

        return arrayList;
    }

    public void addToCart(String id, Context mContext) {
        CartItem cartItemfood = new CartItem(id, 1);
        ArrayList<CartItem> listFood = getCart();
        listFood.add(cartItemfood);
        saveCartQuantity(mContext, listFood);
    }

    public void saveCartQuantity(Context mContext, ArrayList<CartItem> arrayListcartItem) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = new Gson().toJson(arrayListcartItem);
        editor.putString(ITEM_KEY, json);
        editor.apply();
    }

    public boolean isInCart(String id, Context context) {
        ArrayList<CartItem> listCart = getCart();
        for (int i = 0; i < listCart.size(); i++) {
            if (listCart.get(i).getId().equals(id)) return true;
        }
        return false;
    }

    public void updateQuantity(String id, int newQuantity) {
        ArrayList<CartItem> listCart = getCart();
        for (int i = 0; i < listCart.size(); i++) {
            if (listCart.get(i).getId().equals(id)) {
                listCart.get(i).setQuantity(newQuantity);
                saveCartQuantity(mContext, listCart);
                break;
            }
        }
    }

    public CartItem getCartObject(String id) {
        ArrayList<CartItem> listCart = getCart();
        for (int i = 0; i < listCart.size(); i++) {
            if (listCart.get(i).getId().equals(id)) {
                return listCart.get(i);
            }
        }

        return null;
    }

    public void remove(String id, Context context) {
        ArrayList<CartItem> listCart = getCart();
        for (int i = 0; i < listCart.size(); i++) {
            if (listCart.get(i).getId().equals(id)) {
                listCart.remove(i);
                saveCartQuantity(context, listCart);
                break;
            }
        }
    }


}
