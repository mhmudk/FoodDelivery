package com.example.fooddelivery.Delivery.Registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.fooddelivery.pojo.LoginViewFatory;
import com.example.fooddelivery.Manager.CreateAccount;
import com.example.fooddelivery.R;

public class DeliveryRegistration extends AppCompatActivity {
    EditText name, email, phone, password, location;
    Button regist;
    DeliveryViewModel viewmodel;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        findview();
        LoginViewFatory fatory = new LoginViewFatory(this);
        viewmodel = ViewModelProviders.of(this, fatory).get(DeliveryViewModel.class);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getName, getEmail, getPassword, getPhone, getLocation, id;

                getName = name.getText().toString().trim();
                getPassword = password.getText().toString().trim();
                getPhone = phone.getText().toString().trim();
                getEmail = email.getText().toString().trim();
                getLocation = location.getText().toString().trim();
                if (getName.isEmpty() && getEmail.isEmpty() && getPhone.isEmpty() && getLocation.isEmpty() && (getPassword.isEmpty())) {
                    Toast.makeText(DeliveryRegistration.this, "Fields are required", Toast.LENGTH_SHORT).show();
                } else {

                    if (getName.isEmpty() || getEmail.isEmpty() || getPhone.isEmpty() || getLocation.isEmpty() || (getPassword.isEmpty() && getPassword.length() < 6)) {
                        Toast.makeText(DeliveryRegistration.this, "Please fill Fields", Toast.LENGTH_SHORT).show();
                    } else {
                        viewmodel.SignUp(getEmail, getPassword, getName, getEmail, getPhone, getPassword, "Delivery", getLocation);
                    }
                }


            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CreateAccount.class));
            }
        });
    }

    public void findview() {
        name = findViewById(R.id.name_delivery);
        email = findViewById(R.id.email_delivery);
        phone = findViewById(R.id.phoneee_delivery);
        password = findViewById(R.id.passw_delivery);
        location = findViewById(R.id.location_delivery);
        regist = findViewById(R.id.regist_delivery);
        back = findViewById(R.id.back_signup_delivery);
    }
}