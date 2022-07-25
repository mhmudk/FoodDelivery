package com.example.fooddelivery.Delivery;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.fooddelivery.Delivery.ProfileDelivery.Profile.FragmentProfile_Delivery;
import com.example.fooddelivery.Delivery.ProfileDelivery.Orders.FragmentViewDelivery;
import com.example.fooddelivery.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DeliveryPannel extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView navigationView;
    Boolean pressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_pannel);
        navigationView = findViewById(R.id.bottom_navigation_deliverpannel);
        navigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.order_delivery:
                fragment = new FragmentViewDelivery();
                break;
            case R.id.users_delivery:
                fragment = new FragmentProfile_Delivery();
                break;


        }
        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.adminpannel_delivery, fragment).commit();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (pressed) {

            super.onBackPressed();
        } else {
            return;
        }
    }
}