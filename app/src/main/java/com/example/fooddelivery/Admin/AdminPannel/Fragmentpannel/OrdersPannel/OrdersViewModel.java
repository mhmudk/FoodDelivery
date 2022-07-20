package com.example.fooddelivery.Admin.AdminPannel.Fragmentpannel.OrdersPannel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery.pojo.Order;

import java.util.ArrayList;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class OrdersViewModel extends ViewModel {
    Context context;
    RepoOrdersPannel repo = new RepoOrdersPannel();
MutableLiveData<ArrayList<Order>> mutableLiveData = new MutableLiveData<>();
    public OrdersViewModel(Context context) {
        this.context = context;

   }

  public void getOrders(){
        repo.getOrdersFromFirebase().subscribeWith(new DisposableSingleObserver<ArrayList<Order>>() {
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
