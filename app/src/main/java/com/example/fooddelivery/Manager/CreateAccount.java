package com.example.fooddelivery.Manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fooddelivery.Admin.Registration.AdminRegistration;
import com.example.fooddelivery.Customer.CustomerRegistration.CustomerRegistration;
import com.example.fooddelivery.Delivery.Registration.DeliveryRegistration;
import com.example.fooddelivery.Login.LogInRegistration.LogIn;
import com.example.fooddelivery.R;

public class CreateAccount extends AppCompatActivity implements View.OnClickListener {
    ImageView owner, ownerCheck, customer, customerCheck, delivery, deliveryCheck,back;
    Button next;

    Intent intent = null;
    int curuntSelected = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        findviwe();
        onClickview();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(curuntSelected==-1){
                    Toast.makeText(CreateAccount.this, "You have to choose ", Toast.LENGTH_SHORT).show();
                return;
                }
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LogIn.class));
            }
        });
    }

    public void loginManager(View v) {

        if(curuntSelected != -1){
            findViewById(curuntSelected).setVisibility(View.GONE);
        }
        switch (v.getId()) {
            case R.id.imageView13:
                ownerCheck.setVisibility(View.VISIBLE);
                curuntSelected = R.id.imageView13;
                curuntSelected = R.id.imageView17;

                intent = new Intent(CreateAccount.this, AdminRegistration.class);
                break;
            case R.id.imageView14:
                customerCheck.setVisibility(View.VISIBLE);
                curuntSelected = R.id.imageView14;
                curuntSelected = R.id.imageView18;

                intent = new Intent(CreateAccount.this, CustomerRegistration.class);

                break;
            case R.id.imageView15:
                deliveryCheck.setVisibility(View.VISIBLE);
              curuntSelected = R.id.imageView15;
              curuntSelected = R.id.imageView21;
                intent = new Intent(CreateAccount.this, DeliveryRegistration.class);
                break;
        }
    }

    private void findviwe() {
        owner = findViewById(R.id.imageView13);
        customer = findViewById(R.id.imageView14);
        delivery = findViewById(R.id.imageView15);
        ownerCheck = findViewById(R.id.imageView17);
        customerCheck = findViewById(R.id.imageView18);
        deliveryCheck = findViewById(R.id.imageView21);
        next = findViewById(R.id.cont);
        back = findViewById(R.id.back_createaccount);

    }

    public void onClickview() {
        owner.setOnClickListener(this);
        ownerCheck.setOnClickListener(this);
        delivery.setOnClickListener(this);
        deliveryCheck.setOnClickListener(this);
        customer.setOnClickListener(this);
        customerCheck.setOnClickListener(this);
        next.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        loginManager(view);
    }
}