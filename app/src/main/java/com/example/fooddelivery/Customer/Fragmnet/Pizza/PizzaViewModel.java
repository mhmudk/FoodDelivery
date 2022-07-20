package com.example.fooddelivery.Customer.Fragmnet.Pizza;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery.pojo.Food;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class PizzaViewModel extends ViewModel {
    MutableLiveData<List<Food>> mutableLiveData = new MutableLiveData<>();
    RepoOfPizza repoOfPizza = new RepoOfPizza();
Context context ;

    public PizzaViewModel(Context context) {
        this.context = context;
    }

    public void getPizzaData() {
        repoOfPizza.getPizza().subscribeWith(new DisposableSingleObserver<List<Food>>() {
            @Override
            public void onSuccess(@NonNull List<Food> foods) {
                mutableLiveData.postValue(foods);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }
}
