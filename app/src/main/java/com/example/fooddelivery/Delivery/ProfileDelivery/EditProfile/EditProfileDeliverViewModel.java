package com.example.fooddelivery.Delivery.ProfileDelivery.EditProfile;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery.pojo.User;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class EditProfileDeliverViewModel extends ViewModel {
    Context context ;
RepoOfEditProfileDelivery repoOfDeliveryProfile = new RepoOfEditProfileDelivery();
MutableLiveData<User>  mutableLiveData = new MutableLiveData<>();
public EditProfileDeliverViewModel(Context context) {
        this.context = context;
    }
    public void getInformation(){
        repoOfDeliveryProfile.getInfo().subscribeWith(new DisposableSingleObserver<User>() {
            @Override
            public void onSuccess(@NonNull User user) {
                mutableLiveData.postValue(user);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }
}
