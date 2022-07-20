package com.example.fooddelivery.Admin.Registration;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.fooddelivery.Manager.LoginManager;
import com.example.fooddelivery.pojo.User;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class AdminViewModel extends ViewModel {
    Context context;
    Repo repo = new Repo();

    public AdminViewModel(Context context) {
        this.context = context;
    }
    public void SignUp(String email, String password,String name,String email_reg,String phone,String password_reg,String userType,String getLocation){
        repo.CreateEmail(email,password).subscribeWith(new DisposableSingleObserver<Boolean>() {
            @Override
            public void onSuccess(@NonNull Boolean aBoolean) {
                repo.SavingData(new User(name,email_reg,phone,password_reg,userType,getLocation)).subscribeWith(new DisposableSingleObserver<Boolean>() {
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
    }


}
