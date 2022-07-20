package com.example.fooddelivery.Admin.AdminPannel.Fragmentpannel.DeliveryPannel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery.pojo.User;

import java.util.ArrayList;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class DeliveryViewModel extends ViewModel {
    Context context;

    RepoDeliveryPannel repo = new RepoDeliveryPannel();
MutableLiveData<ArrayList<User>> mutableLiveData = new MutableLiveData<>();
    public DeliveryViewModel(Context context) {
        this.context = context;
    }

    public void getdeliveryData() {
        repo.getDeliveryData().subscribeWith(new DisposableSingleObserver<ArrayList<User>>() {
            @Override
            public void onSuccess(@NonNull ArrayList<User> arrayList) {
                mutableLiveData.postValue(arrayList);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }

}
