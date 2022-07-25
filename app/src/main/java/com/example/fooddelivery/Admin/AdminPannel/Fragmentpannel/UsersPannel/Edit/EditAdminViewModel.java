package com.example.fooddelivery.Admin.AdminPannel.Fragmentpannel.UsersPannel.Edit;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery.pojo.User;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class EditAdminViewModel extends ViewModel {
    MutableLiveData<User> mutableLiveData = new MutableLiveData<>();
    Context context;
    EditAdminRepo repo = new EditAdminRepo();

    public EditAdminViewModel(Context context) {
        this.context = context;
    }

    public void getInfoEdit() {
        repo.getProfileDetails().subscribeWith(new DisposableSingleObserver<User>() {
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
