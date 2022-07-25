package com.example.fooddelivery.Customer.Fragmnet.Fries;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery.pojo.Food;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class FriesViewModel extends ViewModel {
    RepoOfFries repoOfFries = new RepoOfFries();
    MutableLiveData<List<Food>> mFoodList = new MutableLiveData<>();
    Context context;

    public FriesViewModel(Context context) {
        this.context = context;
    }

    public void getFriesData() {
        repoOfFries.getFries().subscribeWith(new DisposableSingleObserver<List<Food>>() {
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
