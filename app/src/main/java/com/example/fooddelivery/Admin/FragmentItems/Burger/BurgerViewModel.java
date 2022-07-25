package com.example.fooddelivery.Admin.FragmentItems.Burger;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.fooddelivery.pojo.Food;

public class BurgerViewModel extends ViewModel implements RepoBurger.BurgerInterface {
    Context context;
    RepoBurger repoBurger = new RepoBurger(this);

    public BurgerViewModel(Context context) {
        this.context = context;
    }

    public void pushh(Food food, Uri u) {
        repoBurger.push(food, u);
    }


    @Override
    public void Successed(Food food) {

        Toast.makeText(context, "Successed ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Faild(String error) {

        Toast.makeText(context, "Faild" + error, Toast.LENGTH_LONG).show();
    }
}
