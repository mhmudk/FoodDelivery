package com.example.fooddelivery.Customer.CustomerRegistration;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.fooddelivery.Manager.LoginManager;
import com.example.fooddelivery.pojo.User;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class CustomerViewModel extends ViewModel {
    RepoC repoC = new RepoC();
    Context context;


    public CustomerViewModel(Context context) {
        this.context = context;
    }

    public void SignUp(String email, String password, String name, String email_reg, String phone, String password_reg, String usertype, String getLocation) {
        repoC.CreateEmail(email, password).subscribeWith(new DisposableSingleObserver<Boolean>() {
            @Override
            public void onSuccess(@NonNull Boolean aBoolean) {
                repoC.Saving(new User(name, email_reg, phone, password_reg, usertype, getLocation)).subscribeWith(new DisposableSingleObserver<Boolean>() {
                    @Override
                    public void onSuccess(@NonNull Boolean aBoolean) {
                        Log.d("aaaaaaaa", "success");

                        LoginManager loginManager = new LoginManager(context);
                        loginManager.Login();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("aaaaaaaa", "Error");


                        Toast.makeText(context, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Toast.makeText(context, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}
