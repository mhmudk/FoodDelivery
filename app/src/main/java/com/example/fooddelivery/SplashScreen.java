package com.example.fooddelivery;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fooddelivery.Manager.LoginManager;
import com.example.fooddelivery.ViewFlipper.Viewflipper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {
    FirebaseUser user   = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if(user== null) {
            startActivity(new Intent(this, Viewflipper.class));
        } else {
            LoginManager loginManager = new LoginManager(this);
            loginManager.Login();
        }
    }
}