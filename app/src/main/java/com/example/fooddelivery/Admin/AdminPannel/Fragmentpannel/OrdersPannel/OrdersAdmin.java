package com.example.fooddelivery.Admin.AdminPannel.Fragmentpannel.OrdersPannel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.Adapters.AdminOrdersAdapter;
import com.example.fooddelivery.Admin.AdminPannel.Fragmentpannel.DashboardFragment.IOnAdapterClickListener;
import com.example.fooddelivery.Delivery.Couriers.Couriers;
import com.example.fooddelivery.pojo.LoginViewFatory;
import com.example.fooddelivery.R;
import com.example.fooddelivery.pojo.Order;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class OrdersAdmin extends AppCompatActivity implements IOnAdapterClickListener {
    OrdersViewModel ordersViewModel;
    RecyclerView recyclerView;
    AdminOrdersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_admin);
        recyclerView = findViewById(R.id.orders_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LoginViewFatory factory = new LoginViewFatory(getApplicationContext());
        ordersViewModel = ViewModelProviders.of(this, factory).get(OrdersViewModel.class);
        ordersViewModel.mutableLiveData.observe(this, new Observer<ArrayList<Order>>() {
            @Override
            public void onChanged(ArrayList<Order> orders) {
                updateAdapter(orders);

            }
        });
        ordersViewModel.getOrders();
    }

    private void updateAdapter(ArrayList<Order> orderArrayList) {
        adapter = new AdminOrdersAdapter(orderArrayList, this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onAcceptClickListener(String id) {

        Intent intent = new Intent(getApplicationContext(), Couriers.class);
        intent.putExtra("id", id);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);
    }

    @Override
    public void onRejectClickListener(int position) {
        DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference("Orders");

        String id = ordersViewModel.mutableLiveData.getValue().get(position).getOrderId();
        orderRef.child(id).removeValue();
        ordersViewModel.mutableLiveData.getValue().remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), "Itenm Removed", Toast.LENGTH_SHORT).show();
    }

}
