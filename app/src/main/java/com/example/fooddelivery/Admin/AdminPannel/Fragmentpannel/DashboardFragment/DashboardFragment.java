package com.example.fooddelivery.Admin.AdminPannel.Fragmentpannel.DashboardFragment;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.fooddelivery.Admin.AdminPannel.Fragmentpannel.OrdersPannel.OrdersAdmin;
import com.example.fooddelivery.Admin.Registration.AdminPush;
import com.example.fooddelivery.pojo.LoginViewFatory;
import com.example.fooddelivery.R;


public class DashboardFragment extends Fragment {
    TextView numberOfdelivery, numberOfusers, numberOforders;
    Button btn_orders, btn_food,search;
    ImageView menu;
    DashBoardViewModel dashBoardViewModel;


    public DashboardFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dachboard, container, false);
        findView(v);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),FoodItem.class));
            }
        });
        btn_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AdminPush.class));
            }
        });
        btn_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), OrdersAdmin.class));
            }
        });
        LoginViewFatory fatory = new LoginViewFatory(getApplicationContext());
  dashBoardViewModel = ViewModelProviders.of(this, fatory).get(DashBoardViewModel.class);
search.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(),FoodItem.class));
    }
});

dashBoardViewModel.mutableLiveData.observe(requireActivity(), new Observer<Integer>() {
    @Override
    public void onChanged(Integer integer) {
        updateUi(integer);
    }
});
dashBoardViewModel.getData();
        return v;
    }

    public void findView(View v) {
        btn_orders = v.findViewById(R.id.orders_admin);
        btn_food = v.findViewById(R.id.food_admin);
        numberOfdelivery = v.findViewById(R.id.count_delivery);
        numberOforders = v.findViewById(R.id.count_orders);
        numberOfusers = v.findViewById(R.id.count_users);
        menu = v.findViewById(R.id.goto_menu);
        search = v.findViewById(R.id.search_admin_menu);
    }

    public void updateUi( int  count ){
        numberOfusers.setText(count+"");
        numberOfdelivery.setText(count+"");
        numberOforders.setText(count+"");
    }

}