package com.example.fooddelivery.Admin.Registration;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.fooddelivery.Admin.FragmentItems.Burger.FragmentBurger;
import com.example.fooddelivery.Admin.FragmentItems.Dessert.FragmnetDessert;
import com.example.fooddelivery.Admin.FragmentItems.Drink.FragmentDrink;
import com.example.fooddelivery.Admin.FragmentItems.Fries.FragmentFries;
import com.example.fooddelivery.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminPush extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_push);
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment=null;
        switch (item.getItemId()){
            case R.id.burger:
                fragment = new FragmentBurger();
                break;
            case R.id.drink:
                fragment = new FragmentDrink();
                break;
            case R.id.Pizza:
                fragment = new FragmnetDessert();
                break;
            case R.id.freiss_admin:
                fragment = new FragmentFries();
                break;
        }
        return loadcheffragment(fragment);
    }

    private boolean loadcheffragment(Fragment fragment) {
        if (fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,fragment).commit();
            return true;
        }
        return false;
    }

}