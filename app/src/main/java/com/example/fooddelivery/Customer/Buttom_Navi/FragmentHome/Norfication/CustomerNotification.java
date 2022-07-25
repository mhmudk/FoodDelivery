package com.example.fooddelivery.Customer.Buttom_Navi.FragmentHome.Norfication;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.Adapters.NotificationAdapater;
import com.example.fooddelivery.R;
import com.example.fooddelivery.pojo.LoginViewFatory;
import com.example.fooddelivery.pojo.Order;

import java.util.List;

public class CustomerNotification extends AppCompatActivity {
    NotificationViewModel notificationViewModel;
    NotificationAdapater adapter;
    RecyclerView rec;
    LinearLayout secondView;
    sentNoification sentNoification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        rec = findViewById(R.id.rec_notifity);
        secondView = findViewById(R.id.notification_secondView);
        rec.setLayoutManager(new LinearLayoutManager(this));
        LoginViewFatory fatory = new LoginViewFatory(getApplicationContext());
        notificationViewModel = ViewModelProviders.of(this, fatory).get(NotificationViewModel.class);
        notificationViewModel.mutableLiveData.observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                if (orders.size() == 0) {
                    rec.setVisibility(View.GONE);
                    secondView.setVisibility(View.VISIBLE);
                    sentNoification.notification(false);
                }

                update(orders);
            }
        });
        notificationViewModel.getNotification();
    }

    public void update(List<Order> orders) {
        adapter = new NotificationAdapater(getApplicationContext(), orders);
        rec.setAdapter(adapter);
    }

    public interface sentNoification {
        void notification(boolean state);
    }
}