package com.example.fooddelivery.Customer.CustomerRegistration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.fooddelivery.Login.LogInRegistration.LogIn;
import com.example.fooddelivery.pojo.LoginViewFatory;
import com.example.fooddelivery.Manager.CreateAccount;
import com.example.fooddelivery.R;

public class CustomerRegistration extends AppCompatActivity {
    EditText name, email, phone, password, location, confirmPassword;
    Button regist;
    CustomerViewModel viewModel;
    ImageView back;
    TextView logCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        findbyid();
        LoginViewFatory fatory = new LoginViewFatory(this);
        viewModel = ViewModelProviders.of(this, fatory).get(CustomerViewModel.class);

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getName = name.getText().toString().trim();
                String getPassword = password.getText().toString().trim();
                String getPhone = phone.getText().toString().trim();
                String getEmail = email.getText().toString().trim();
                String getLocation = location.getText().toString().trim();
                String getConfirmPassword = confirmPassword.getText().toString().trim();

                if (getName.isEmpty() && getEmail.isEmpty() && getPhone.isEmpty() && getLocation.isEmpty() && getPassword.isEmpty()) {
                    Toast.makeText(CustomerRegistration.this, "Fields are required", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (getName.isEmpty() || getEmail.isEmpty() || getPhone.isEmpty() || getLocation.isEmpty() || getPassword.isEmpty()) {

                        Toast.makeText(CustomerRegistration.this, "Please Fill Fields", Toast.LENGTH_SHORT).show();
                    } else {
                        viewModel.SignUp(getEmail, getPassword, getName, getEmail, getPhone, getPassword, "Customer", getLocation);

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
        logCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LogIn.class));
            }
        });


    }

    public void findbyid() {

        name = findViewById(R.id.name_customer);
        logCustomer = findViewById(R.id.log_customer);
        email = findViewById(R.id.email_customer);
        phone = findViewById(R.id.phoneee_customer);
        password = findViewById(R.id.passw_customer);
        location = findViewById(R.id.location_customer);
        regist = findViewById(R.id.regist_customer);
        confirmPassword = findViewById(R.id.confirm_customer);
        back = findViewById(R.id.back_signup_customer);
    }
}