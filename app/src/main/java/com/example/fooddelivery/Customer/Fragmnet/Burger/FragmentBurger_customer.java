package com.example.fooddelivery.Customer.Fragmnet.Burger;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.Adapters.FoodAdapter;
import com.example.fooddelivery.R;
import com.example.fooddelivery.pojo.Food;
import com.example.fooddelivery.pojo.LoginViewFatory;

import java.util.ArrayList;
import java.util.List;

public class FragmentBurger_customer extends Fragment {

    RecyclerView recyclerView;
    FoodAdapter adapterOfFood;
    CustomerBurgerViewModel burgerViewModel;

    public FragmentBurger_customer() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_burger2, container, false);

        recyclerView = v.findViewById(R.id.rec_burger_Cust);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        LoginViewFatory fatory = new LoginViewFatory(getApplicationContext());
        burgerViewModel = ViewModelProviders.of(this, fatory).get(CustomerBurgerViewModel.class);

        burgerViewModel.mFoodList.observe(requireActivity(), new Observer<List<Food>>() {
            @Override
            public void onChanged(List<Food> foods) {
                updateAdapter(new ArrayList(foods));
            }
        });
        burgerViewModel.getBurger();
        return v;
    }


    private void updateAdapter(ArrayList<Food> listFood) {
        adapterOfFood = new FoodAdapter(listFood, getContext());
        recyclerView.setAdapter(adapterOfFood);

    }

}