package com.example.fooddelivery.Login.LogInRegistration;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.fooddelivery.Manager.LoginManager;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class LoginViewModel extends ViewModel {
    Context context;

    LoginRepo log = new LoginRepo();

    public LoginViewModel(Context context) {
        this.context = context;
    }

    public void LogIn(String email, String password) {
        log.Login(email, password).subscribeWith(new DisposableSingleObserver<Boolean>() {
            @Override
            public void onSuccess(@NonNull Boolean aBoolean) {
                LoginManager loginManager = new LoginManager(context);
                loginManager.Login();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Toast.makeText(context, "Faild logged in " + e.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }


}
