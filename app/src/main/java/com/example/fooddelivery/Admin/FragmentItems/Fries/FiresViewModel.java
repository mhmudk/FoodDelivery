package com.example.fooddelivery.Admin.FragmentItems.Fries;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.fooddelivery.pojo.Food;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class FiresViewModel extends ViewModel {
    Context context;
    RepoFries repoBurger = new RepoFries();

    public void pushh(Food food, Uri u) {
        repoBurger.push(food, u).subscribeWith(new DisposableSingleObserver<Boolean>() {
            @Override
            public void onSuccess(@NonNull Boolean aBoolean) {
                Toast.makeText(context, "Successed ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Toast.makeText(context, "Faild" + e, Toast.LENGTH_LONG).show();
            }
        });
    }


}
