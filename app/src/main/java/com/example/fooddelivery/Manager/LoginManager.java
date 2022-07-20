package com.example.fooddelivery.Manager;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.example.fooddelivery.Admin.AdminPannel.AdminPannel;
import com.example.fooddelivery.Customer.ViewCustomer;
import com.example.fooddelivery.Delivery.DeliveryPannel;
import com.example.fooddelivery.pojo.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginManager {

    Context context;
    FirebaseAuth mAuth;

    public LoginManager(Context context) {
        this.context = context;
        mAuth = FirebaseAuth.getInstance();
    }

    public void Login() {
        if (mAuth.getCurrentUser() == null) return;
        getUserData();
    }

    private void getUserData() {
        if(mAuth==null)
            mAuth=FirebaseAuth.getInstance();
        String uId = mAuth.getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference("Users").child(uId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                navigat(user.getUsertype());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    // ثوانى معلشي ياهندسه بقولك انه اصلا مش بيرضي يستدعي Saving function ال ف الريبو
    private void navigat(String userType) {
        if (userType.equals("Admin")) {
            context.startActivity(new Intent(context, AdminPannel.class));
        } else if (userType.equals("Customer")) {
            context.startActivity(new Intent(context, ViewCustomer.class));
        } else if (userType.equals("Delivery")) {
            context.startActivity(new Intent(context, DeliveryPannel.class));
        }
    }
    // بص ياهندسه دلوقتي انا باجى اعمل Regis تمام بيقولي ان الريفرنس دا بنل مش عارف لي
}
