package com.example.fooddelivery.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fooddelivery.R;
import com.facebook.login.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {
    EditText email;
    Button send;
    ImageView back;
    String getEmail;
    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        fauth = FirebaseAuth.getInstance();
        findview();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEmail = email.getText().toString();

                if (getEmail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(getEmail).matches()) {

                    Toast.makeText(getApplicationContext(), "Email is Invalid", Toast.LENGTH_SHORT).show();
                } else {
                    fauth.sendPasswordResetEmail(getEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Check Your Email", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Error" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }

    public void findview() {
        email = findViewById(R.id.email_forgett);
        send = findViewById(R.id.send_button);
        back = findViewById(R.id.backtologin);

    }
}
