package com.example.fooddelivery.Delivery.ProfileDelivery;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fooddelivery.Login.ForgetPassword;
import com.example.fooddelivery.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class ChangePasswordProfile_Delivery extends AppCompatActivity {
    EditText oldpassword, newpassword;
    TextView forgetPassword;
    Button save;
    ImageView back;
    String getoldPassword, getnewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_profile_delivery);
        findview();


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changepassword();
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ForgetPassword.class));
            }
        });
    }

    public void findview() {
        oldpassword = findViewById(R.id.pass_new_deliv);
        newpassword = findViewById(R.id.passconf_new_deliv);
        forgetPassword = findViewById(R.id.forget_profilepassword_deliv);
        save = findViewById(R.id.save_newpassword_deliv);
        back = findViewById(R.id.back_toprofile_deliv);

    }

    public void changepassword() {
        getoldPassword = oldpassword.getText().toString();
        getnewPassword = newpassword.getText().toString();
        FirebaseUser user;
        user = FirebaseAuth.getInstance().getCurrentUser();
        String getEmail = user.getEmail();
        if (TextUtils.isEmpty(getoldPassword) || getEmail == null || TextUtils.isEmpty(getEmail)) {
            return;
        }
    user.updatePassword(getnewPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d("TAG", "true");

                    FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("password").setValue(getnewPassword);
                    Toast.makeText(ChangePasswordProfile_Delivery.this, "Password Successfully changed", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("TAG", task.getException().getMessage().toString());
                    Toast.makeText(ChangePasswordProfile_Delivery.this, "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}



