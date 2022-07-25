package com.example.fooddelivery.Customer.Fragmnet.Pizza;

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
import com.example.fooddelivery.Admin.AdminPannel.Fragmentpannel.DashboardFragment.DashBoardViewModel;
import com.example.fooddelivery.R;
import com.example.fooddelivery.pojo.Food;
import com.example.fooddelivery.pojo.LoginViewFatory;

import java.util.ArrayList;
import java.util.List;

public class FragmentPizza_customer extends Fragment {

    public FragmentPizza_customer() {

    }

    RecyclerView rec;
    PizzaViewModel pizzaViewModel;
    FoodAdapter foodAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dessert, container, false);
        rec = v.findViewById(R.id.rec_dessert);
        rec.setLayoutManager(new LinearLayoutManager(getContext()));
        LoginViewFatory fatory = new LoginViewFatory(getApplicationContext());
        pizzaViewModel = ViewModelProviders.of(this, fatory).get(PizzaViewModel.class);
        pizzaViewModel.mutableLiveData.observe(requireActivity(), new Observer<List<Food>>() {
            @Override
            public void onChanged(List<Food> foods) {
                updateAdapter(new ArrayList(foods));
            }
        });
        pizzaViewModel.getPizzaData();
        return v;
    }


    private void updateAdapter(ArrayList<Food> listFood) {
        foodAdapter = new FoodAdapter(listFood, getContext());
        rec.setAdapter(foodAdapter);


    }


}