package com.example.fooddelivery.Customer.Fragmnet.Burger;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery.pojo.Food;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class CustomerBurgerViewModel extends ViewModel {
    Context context;
    RepoOfBurger repoOfBurger = new RepoOfBurger();
    MutableLiveData<List<Food>> mFoodList = new MutableLiveData<>();

    public CustomerBurgerViewModel(Context context) {
        this.context = context;
    }

    public void getBurger() {
        repoOfBurger.getBurgerData().subscribeWith(new DisposableSingleObserver<List<Food>>() {
            @Override
            public void onSuccess(@NonNull List<Food> foods) {
                mFoodList.postValue(foods);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }
}
