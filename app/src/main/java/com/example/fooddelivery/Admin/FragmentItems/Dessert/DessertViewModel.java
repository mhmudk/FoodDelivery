package com.example.fooddelivery.Admin.FragmentItems.Dessert;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.fooddelivery.pojo.Food;

public class DessertViewModel extends ViewModel implements RepoDessert.DessertInterface {
    Context context;
    RepoDessert repoDessert = new RepoDessert(this);

    public DessertViewModel(Context context) {
        this.context = context;
    }

    public void Uploaddata(Food food, Uri filePath) {
        repoDessert.startPushing(food, filePath);
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
