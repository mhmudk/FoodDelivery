package com.example.fooddelivery.Customer.Buttom_Navi.FragmentHome.Norfication;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery.pojo.Order;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class NotificationViewModel extends ViewModel {
    RepoNotification repoNotification = new RepoNotification();
    Context context;
MutableLiveData<List<Order>> mutableLiveData  =new MutableLiveData<>();
    public NotificationViewModel(Context context) {
        this.context = context;
    }
    public void getNotification(){
        repoNotification.getNotificationOrdres().subscribeWith(new DisposableSingleObserver<List<Order>>() {
            @Override
            public void onSuccess(@NonNull List<Order> orders) {
                mutableLiveData.postValue(orders);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }
}
