package com.example.fooddelivery.Customer.Fragmnet.Drinks;

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

public class FragmentDrinks_customer extends Fragment {

    public FragmentDrinks_customer() {
        // Required empty public constructor
    }

    DrinksViewModel drinksViewModel;
    RecyclerView rec;
    FoodAdapter foodAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_drinks, container, false);
        rec = v.findViewById(R.id.recyclerView2_drink);
        rec.setLayoutManager(new LinearLayoutManager(getContext()));
        LoginViewFatory fatory = new LoginViewFatory(getApplicationContext());
        drinksViewModel = ViewModelProviders.of(this, fatory).get(DrinksViewModel.class);
        drinksViewModel.mFoodList.observe(requireActivity(), new Observer<List<Food>>() {
            @Override
            public void onChanged(List<Food> foods) {
                updateAdapter(new ArrayList(foods));
            }
        });
        drinksViewModel.getDrinks();
        return v;
    }


    private void updateAdapter(ArrayList<Food> listFood) {
        foodAdapter = new FoodAdapter(listFood, getContext());
        rec.setAdapter(foodAdapter);


    }

}