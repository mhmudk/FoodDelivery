package com.example.fooddelivery.Delivery.Registration;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.fooddelivery.Manager.LoginManager;
import com.example.fooddelivery.pojo.User;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class DeliveryViewModel extends ViewModel {
    Context context;
    RepoDelivery repoDelivery = new RepoDelivery();

    public DeliveryViewModel(Context context) {
        this.context = context;
    }

    public void SignUp(String email, String password, String getName, String getEmail, String getPhone, String getPassword, String getuserType, String getLocation) {
        repoDelivery.CreateEmail(email, password).subscribeWith(new DisposableSingleObserver<Boolean>() {
            @Override
            public void onSuccess(@NonNull Boolean aBoolean) {
                repoDelivery.Saving(new User(getName, getEmail, getPhone, getPassword, getuserType, getLocation)).subscribeWith(new DisposableSingleObserver<Boolean>() {
                    @Override
                    public void onSuccess(@NonNull Boolean aBoolean) {
                        LoginManager loginManager = new LoginManager(context);
                        loginManager.Login();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(context, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Toast.makeText(context, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        repoDelivery.Saving(new User(getName, getEmail, getPhone, getPassword, getuserType, getLocation)).subscribeWith(new DisposableSingleObserver<Boolean>() {
            @Override
            public void onSuccess(@NonNull Boolean aBoolean) {
                LoginManager loginManager = new LoginManager(context);
                loginManager.Login();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Toast.makeText(context, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}
