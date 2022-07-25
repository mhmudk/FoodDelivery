package com.example.fooddelivery.Delivery.ProfileDelivery.Orders;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery.pojo.Order;

import java.util.ArrayList;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class OrdersOfDeliveryViewModel extends ViewModel {
    RepoOFOrdersDelivery repo = new RepoOFOrdersDelivery();
    Context context;
    MutableLiveData<ArrayList<Order>> mutableLiveData = new MutableLiveData<>();

    public OrdersOfDeliveryViewModel(Context context) {
        this.context = context;
    }

    public void getOrders() {
        repo.getOrders().subscribeWith(new DisposableSingleObserver<ArrayList<Order>>() {
            @Override
            public void onSuccess(@NonNull ArrayList<Order> orders) {
                mutableLiveData.postValue(orders);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }
}
