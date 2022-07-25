package com.example.fooddelivery.Customer.Fragmnet.Drinks;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery.pojo.Food;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class DrinksViewModel extends ViewModel {

    RepoOfDrinks repoOfDrinks = new RepoOfDrinks();

    MutableLiveData<List<Food>> mFoodList = new MutableLiveData<>();
    Context context;

    public DrinksViewModel(Context context) {
        this.context = context;
    }

    public void getDrinks() {
        repoOfDrinks.getDrinks().subscribeWith(new DisposableSingleObserver<List<Food>>() {
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
