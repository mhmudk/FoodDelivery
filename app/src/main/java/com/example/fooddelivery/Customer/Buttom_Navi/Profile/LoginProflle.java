package com.example.fooddelivery.Customer.Buttom_Navi.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fooddelivery.Login.LogInRegistration.LogIn;
import com.example.fooddelivery.R;

public class LoginProflle extends AppCompatActivity {
    Button goToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_proflle);
        goToLogin = findViewById(R.id.gotoLogin);
        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LogIn.class));
            }
        });
    }
}