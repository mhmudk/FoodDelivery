package com.example.fooddelivery.Admin.Registration;

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
import com.google.firebase.auth.FirebaseAuth;

public class AdminRegistration extends AppCompatActivity {
    EditText name, email, phone, password, location;
    ImageView back;
    Button register;
    FirebaseAuth Fauth;
    AdminViewModel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        findViewbyID();
        Fauth = FirebaseAuth.getInstance();
        LoginViewFatory factory = new LoginViewFatory(this);
        viewmodel = ViewModelProviders.of(this, factory).get(AdminViewModel.class);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getname, getemail, getphone, getpassowrd, getLocation;
                getname = name.getText().toString().trim();
                getemail = email.getText().toString().trim();
                getphone = phone.getText().toString().trim();
                getpassowrd = password.getText().toString().trim();
                getLocation = location.getText().toString().trim();


                if (getname.isEmpty() && getemail.isEmpty() && getphone.isEmpty() && getLocation.isEmpty() && getpassowrd.isEmpty()) {
                    Toast.makeText(AdminRegistration.this, "Fields are required", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (getname.isEmpty() || getemail.isEmpty() || getphone.isEmpty() || getLocation.isEmpty() || getpassowrd.isEmpty()) {

                        Toast.makeText(AdminRegistration.this, "Please Fill Fields", Toast.LENGTH_SHORT).show();
                    } else {
viewmodel.SignUp(getemail,getpassowrd,getname,getemail,getphone,getpassowrd,"Admin",getLocation);
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

    public void findViewbyID() {
        name = findViewById(R.id.name_admin);
        email = findViewById(R.id.email_admin);
        phone = findViewById(R.id.phoneee_admin);
        password = findViewById(R.id.passw_admin);
        register = findViewById(R.id.regist_admin);
        location = findViewById(R.id.location_admin);
        back = findViewById(R.id.back_signup_admin);
    }
}