package com.example.fooddelivery.Admin.FragmentItems.Drink;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.fooddelivery.pojo.Food;

public class DrinkViewModel extends ViewModel implements RepoDrink.DrinktInterface {
   Context context;
RepoDrink repoDrink = new RepoDrink(this);
    public DrinkViewModel(Context context) {
        this.context = context;
    }
    public void Uploaddata(Food food, Uri filePath){
        repoDrink.StartPushing(food,filePath);
    }

    @Override
    public void Successed() {
        Toast.makeText(context, "Successed ", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void Faild(String error) {
        Toast.makeText(context, "Faild ", Toast.LENGTH_SHORT).show();

    }
}
