package com.example.fooddelivery.Admin.AdminPannel;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.fooddelivery.Admin.AdminPannel.Fragmentpannel.DashboardFragment.DashboardFragment;
import com.example.fooddelivery.Admin.AdminPannel.Fragmentpannel.DeliveryPannel.DeliveryFragment;
import com.example.fooddelivery.Admin.AdminPannel.Fragmentpannel.UsersPannel.Profile.FragmentProfile_Admin;
import com.example.fooddelivery.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminPannel extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener  {
    BottomNavigationView navigationView;
    Boolean pressed = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pannel);
        navigationView = findViewById(R.id.bottom_navigation_adminpannel);
        navigationView.setOnNavigationItemSelectedListener(this);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.home_admin:
                fragment = new DashboardFragment();
                break;
            case R.id.delivery_admin:
                fragment = new DeliveryFragment();
                break;

            case R.id.users_admin:
                fragment = new FragmentProfile_Admin();
                break;

        }
        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.adminpannel, fragment).commit();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if(pressed){

            super.onBackPressed();
        }else{
            return ;
        }
    }

}