package com.example.fooddelivery.Delivery.ProfileDelivery.Orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.Adapters.DeliveryOrderAdapter;
import com.example.fooddelivery.Interfaces.IDoneDeliverey;
import com.example.fooddelivery.R;
import com.example.fooddelivery.pojo.LoginViewFatory;
import com.example.fooddelivery.pojo.Order;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FragmentViewDelivery extends Fragment implements IDoneDeliverey {
    RecyclerView deliveryRec;
    DeliveryOrderAdapter adapter;
    ArrayList<Order> arrayList;
OrdersOfDeliveryViewModel viewModel ;
    DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference("Orders");
    LinearLayout linearSecond;

    public FragmentViewDelivery() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.fragment_view_delivery, container, false);
        findview(v);
        deliveryRec.setLayoutManager(new LinearLayoutManager(getContext()));

        LoginViewFatory fatory = new LoginViewFatory(getContext());
        viewModel = ViewModelProviders.of(this, fatory).get(OrdersOfDeliveryViewModel.class);

viewModel.mutableLiveData.observe(requireActivity(), new Observer<ArrayList<Order>>() {
    @Override
    public void onChanged(ArrayList<Order> orders) {
        if(orders.size() == 0 ){
            deliveryRec.setVisibility(View.GONE);
            linearSecond.setVisibility(View.VISIBLE);
        }
        updateData(orders);
    }
});
viewModel.getOrders();
        return  v;
    }


    private void updateData(ArrayList<Order> arrayList) {
        adapter = new DeliveryOrderAdapter(arrayList, getContext(), this);
        deliveryRec.setAdapter(adapter);

    }

    @Override
    public void done(String position) {
        FirebaseDatabase.getInstance().getReference("Orders")
               .child(position)
             .child("statue").setValue("delivered");


    }

    public void findview(View v  ) {
        deliveryRec = v.findViewById(R.id.delivery_rec);
        linearSecond = v.findViewById(R.id.second_linear);

    }


}