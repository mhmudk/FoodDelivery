package com.example.fooddelivery.Delivery.Couriers;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery.pojo.User;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class CourierViewModel extends ViewModel {
    MutableLiveData<List<User>> mutableLiveData = new MutableLiveData<>();
    Context context;
    RepoOfCourier repoOfCourier = new RepoOfCourier();

    public CourierViewModel(Context context) {
        this.context = context;
    }

    public void getDeliveryCouriers() {
        repoOfCourier.getDeliveryData().subscribeWith(new DisposableSingleObserver<List<User>>() {
            @Override
            public void onSuccess(@NonNull List<User> users) {
                mutableLiveData.postValue(users);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }
}
