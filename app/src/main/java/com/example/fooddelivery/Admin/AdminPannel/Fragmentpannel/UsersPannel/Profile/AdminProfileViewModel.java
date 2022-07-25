package com.example.fooddelivery.Admin.AdminPannel.Fragmentpannel.UsersPannel.Profile;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery.pojo.User;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class AdminProfileViewModel extends ViewModel {
    Context context;
    MutableLiveData<User> mutableLiveData = new MutableLiveData<>();

    public AdminProfileViewModel(Context context) {
        this.context = context;
    }

    RepoOfProfileAdmin repo = new RepoOfProfileAdmin();

    public void getInformation() {
        repo.getInfo().subscribeWith(new DisposableSingleObserver<User>() {
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
