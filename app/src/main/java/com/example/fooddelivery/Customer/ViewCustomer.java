package com.example.fooddelivery.Customer;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.fooddelivery.Customer.Buttom_Navi.FragmentFavourite_Customer;
import com.example.fooddelivery.Customer.Buttom_Navi.FragmentHome.FragmentHome_Customer;
import com.example.fooddelivery.Customer.Buttom_Navi.FragmentShopping_Customer;
import com.example.fooddelivery.Customer.Buttom_Navi.Profile.FragmentProfile_Customer;
import com.example.fooddelivery.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ViewCustomer extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView navigationView;

    Boolean pressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer);

        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        if (pressed) {
            super.onBackPressed();
        } else {
            return;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.home_customer:
                fragment = new FragmentHome_Customer();
                break;
            case R.id.shopping_customer:
                fragment = new FragmentShopping_Customer();
                break;
            case R.id.cart_customer:
                fragment = new FragmentFavourite_Customer();
                break;
            case R.id.profile_customer:
                fragment = new FragmentProfile_Customer();
                break;


        }
        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, fragment).commit();
            return true;
        }
        return false;
    }


}