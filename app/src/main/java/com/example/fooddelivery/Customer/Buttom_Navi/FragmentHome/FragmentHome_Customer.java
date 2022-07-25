package com.example.fooddelivery.Customer.Buttom_Navi.FragmentHome;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.fooddelivery.Customer.Buttom_Navi.FragmentHome.Norfication.CustomerNotification;
import com.example.fooddelivery.Customer.Fragmnet.Burger.FragmentBurger_customer;
import com.example.fooddelivery.Customer.Fragmnet.Pizza.FragmentPizza_customer;
import com.example.fooddelivery.Customer.Fragmnet.Drinks.FragmentDrinks_customer;
import com.example.fooddelivery.Customer.Fragmnet.Fries.FragmentFries_customer;
import com.example.fooddelivery.R;

import java.util.ArrayList;

public class FragmentHome_Customer extends Fragment implements View.OnClickListener, CustomerNotification.sentNoification {
    ImageView drinks, pizza, burger, notification, notifvisible, fries;
    TextView burger_text, drinks_text_, pizza_textview, fries_text;
    int curuntImageSelected = -1, currentTextSelected = -1;
    ImageSlider img;
    Button searchView;

    public FragmentHome_Customer() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        findview(v);
        img();
        clickInterface();
        curuntImageSelected = R.id.burger_cu;
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getContext(), SearchFood.class));
            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CustomerNotification.class));
            }
        });
        return v;
    }


    public void clickInterface() {
        burger.setOnClickListener(this);
        fries.setOnClickListener(this);
        drinks.setOnClickListener(this);
        pizza.setOnClickListener(this);
        pizza_textview.setOnClickListener(this);
        burger.setSelected(true);
        burger_text.setTextColor(ContextCompat.getColor(requireContext(), R.color.maincolor));
        currentTextSelected = R.id.burger_select;
    }

    @Override
    public void onClick(View view) {

        if (curuntImageSelected == view.getId() && currentTextSelected == view.getId()) {
            return;
        }
        if (curuntImageSelected != -1) {
            view.getRootView().findViewById(curuntImageSelected).setSelected(false);
        }
        if (currentTextSelected != -1) {
            ((TextView) view.getRootView().findViewById(currentTextSelected)).setTextColor(Color.parseColor("#000000"));
        }
        Fragment fragment1 = null;
        switch (view.getId()) {
            case R.id.burger_cu:
                fragment1 = new FragmentBurger_customer();
                burger.setSelected(true);
                curuntImageSelected = R.id.burger_cu;
                currentTextSelected = R.id.burger_select;
                burger_text.setTextColor(ContextCompat.getColor(requireContext(), R.color.maincolor));

                break;
            case R.id.drinks_cu:
                fragment1 = new FragmentDrinks_customer();
                drinks.setSelected(true);
                curuntImageSelected = R.id.drinks_cu;
                currentTextSelected = R.id.drink_text;
                drinks_text_.setTextColor(ContextCompat.getColor(requireContext(), R.color.maincolor));
                break;
            case R.id.pizza_cu:
                fragment1 = new FragmentPizza_customer();
                pizza.setSelected(true);
                curuntImageSelected = R.id.pizza_cu;
                currentTextSelected = R.id.pizza_text;
                pizza_textview.setTextColor(ContextCompat.getColor(requireContext(), R.color.maincolor));

                break;
            case R.id.fries_cu:
                fragment1 = new FragmentFries_customer();
                fries.setSelected(true);
                curuntImageSelected = R.id.fries_cu;
                currentTextSelected = R.id.fries_text_id;
                fries_text.setTextColor(ContextCompat.getColor(requireContext(), R.color.maincolor));

                break;
        }
        loadFragment(fragment1);
    }

    public boolean loadFragment(Fragment fragment1) {
        if (fragment1 != null) {
            getChildFragmentManager().beginTransaction().replace(R.id.fragmentContainerView3, fragment1).addToBackStack(null).commit();
            return true;
        }
        return false;
    }

    public void findview(View v) {
        burger = v.findViewById(R.id.burger_cu);
        searchView = v.findViewById(R.id.search_customer);
        drinks = v.findViewById(R.id.drinks_cu);
        pizza = v.findViewById(R.id.pizza_cu);
        burger_text = v.findViewById(R.id.burger_select);
        drinks_text_ = v.findViewById(R.id.drink_text);
        pizza_textview = v.findViewById(R.id.pizza_text);
        fries_text = v.findViewById(R.id.fries_text_id);
        notification = v.findViewById(R.id.notification_cu);
        notifvisible = v.findViewById(R.id.notification_point);
        fries = v.findViewById(R.id.fries_cu);
        img = v.findViewById(R.id.image_slider);
    }

    public void img() {
        ArrayList<SlideModel> array = new ArrayList<>();
        array.add(new SlideModel(R.drawable.offers, ScaleTypes.FIT));
        array.add(new SlideModel(R.drawable.offers1, ScaleTypes.FIT));
        array.add(new SlideModel(R.drawable.offers2, ScaleTypes.FIT));

        img.setImageList(array, ScaleTypes.FIT);

    }

    @Override
    public void notification(boolean state) {
        notifvisible.setVisibility(View.GONE);
    }
}