package com.example.fooddelivery.Delivery.Couriers;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.Adapters.CouriersAdapter;
import com.example.fooddelivery.R;
import com.example.fooddelivery.pojo.LoginViewFatory;
import com.example.fooddelivery.pojo.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Couriers extends AppCompatActivity implements CouriersAdapter.UserClick {
    RecyclerView recyclerView;
    CouriersAdapter adapter;
    String orderId;
    CourierViewModel courierViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_couriers);
        orderId = getIntent().getStringExtra("id");
        recyclerView = findViewById(R.id.couries_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LoginViewFatory fatory = new LoginViewFatory(getApplicationContext());
        courierViewModel = ViewModelProviders.of(this, fatory).get(CourierViewModel.class);
        courierViewModel.mutableLiveData.observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                updateData(new ArrayList(users));
            }
        });
        courierViewModel.getDeliveryCouriers();
    }


    public void updateData(ArrayList<User> arrayList) {
        adapter = new CouriersAdapter(arrayList, getApplicationContext(), this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onUserClick(User user) {
        Log.i("Couriers", "Order Id : " + orderId);
        String uId = user.getId();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().
                getReference("Orders");
        databaseReference.child(orderId).child("courierId").setValue(uId);
        databaseReference.child(orderId).child("statue").setValue("Courier");
        Toast.makeText(this, "Courier Assigned", Toast.LENGTH_SHORT).show();
        finish();
    }
}