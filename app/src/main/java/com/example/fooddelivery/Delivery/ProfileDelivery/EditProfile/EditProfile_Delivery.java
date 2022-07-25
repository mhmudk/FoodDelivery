package com.example.fooddelivery.Delivery.ProfileDelivery.EditProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.fooddelivery.Delivery.DeliveryPannel;
import com.example.fooddelivery.R;
import com.example.fooddelivery.pojo.LoginViewFatory;
import com.example.fooddelivery.pojo.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfile_Delivery extends AppCompatActivity {
    TextView txt_name;
    EditText name_edit, email_edit, mobilenumber_edit, location_edit;
    Button save;
    EditProfileDeliverViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_delivery);
        findView();
        LoginViewFatory fatory = new LoginViewFatory(getApplicationContext());
        viewModel = ViewModelProviders.of(this, fatory).get(EditProfileDeliverViewModel.class);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Edit();
                Toast.makeText(EditProfile_Delivery.this, "Information Updated`", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), DeliveryPannel.class));
            }
        });

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (signInAccount != null) {
            name_edit.setText(signInAccount.getDisplayName());
            email_edit.setText(signInAccount.getEmail());
            mobilenumber_edit.setVisibility(View.GONE);
            location_edit.setVisibility(View.GONE);
        }
        viewModel.mutableLiveData.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                updateUi(user);
            }
        });
        viewModel.getInformation();
    }


    public void updateUi(User user) {
        name_edit.setText(user.getName());
        txt_name.setText(user.getName());
        email_edit.setText(user.getEmail());
        mobilenumber_edit.setText(user.getPhone());
        location_edit.setText(user.getLocation());


    }

    public void findView() {
        name_edit = findViewById(R.id.edit_name_profile_deliv);
        email_edit = findViewById(R.id.edit_email_profile_deliv);
        mobilenumber_edit = findViewById(R.id.edit_mobilenumber_deliv);
        location_edit = findViewById(R.id.edit_location_profile_deliv);
        txt_name = findViewById(R.id.name_formdata_del);
        save = findViewById(R.id.reEdit_deliv);
    }

    public void Edit() {
        DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        String getname, getemail, getphone, getlocation;
        getname = name_edit.getText().toString();
        getemail = email_edit.getText().toString();
        getphone = mobilenumber_edit.getText().toString();
        getlocation = location_edit.getText().toString();
        dataRef.child("name").setValue(getname);
        dataRef.child("email").setValue(getemail);
        dataRef.child("phone").setValue(getphone);
        dataRef.child("location").setValue(getlocation);


    }
}