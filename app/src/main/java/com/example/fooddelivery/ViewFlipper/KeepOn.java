package com.example.fooddelivery.ViewFlipper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fooddelivery.Customer.ViewCustomer;
import com.example.fooddelivery.Manager.CreateAccount;
import com.example.fooddelivery.Login.LogInRegistration.LogIn;
import com.example.fooddelivery.R;

public class KeepOn extends AppCompatActivity {
Button login , create_acc ;
ImageView home ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.keepon);
   findview();
   login.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           startActivity(new Intent(getApplicationContext(), LogIn.class));
       }
   });
   create_acc.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           startActivity(new Intent(getApplicationContext(), CreateAccount.class));
       }

   });
   home.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           startActivity(new Intent(getApplicationContext(), ViewCustomer.class));
       }
   });
    }

    private void findview() {
        login = findViewById(R.id.go_login);
        create_acc = findViewById(R.id.create);
        home = findViewById(R.id.homescreen);
    }

}