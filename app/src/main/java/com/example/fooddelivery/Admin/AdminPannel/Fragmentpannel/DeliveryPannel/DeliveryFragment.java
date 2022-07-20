package com.example.fooddelivery.Admin.AdminPannel.Fragmentpannel.DeliveryPannel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.Adapters.NumberofDeliveryAdapter;
import com.example.fooddelivery.pojo.LoginViewFatory;
import com.example.fooddelivery.R;
import com.example.fooddelivery.pojo.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DeliveryFragment extends Fragment implements NumberofDeliveryAdapter.CourierId {
    String orderId;
    DeliveryViewModel deliveryViewModel;
    RecyclerView rec;
    NumberofDeliveryAdapter adapterdelivery;
    public DeliveryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_delivery, container, false);
        rec = v.findViewById(R.id.rec_delivery_pannel);
        orderId = getActivity().getIntent().getStringExtra("id");
        rec.setLayoutManager(new LinearLayoutManager(getContext()));
        LoginViewFatory factory = new LoginViewFatory(getContext());
        deliveryViewModel = ViewModelProviders.of(this, factory).get(DeliveryViewModel.class);
   deliveryViewModel.mutableLiveData.observe(requireActivity(), new Observer<ArrayList<User>>() {
       @Override
       public void onChanged(ArrayList<User> arrayList) {
           updateDeliveryData(arrayList);
       }
   });
   deliveryViewModel.getdeliveryData();
        return v;
    }
    @Override
    public void sendId(User user) {
        String uId = user.getId();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().
                getReference("Orders");
        databaseReference.child(orderId).child("courierId").setValue(uId);
        databaseReference.child(orderId).child("statue").setValue("Courier");
        Toast.makeText(getActivity(), "Courier Assigned", Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }
    public void updateDeliveryData(ArrayList<User> list){
        adapterdelivery  = new NumberofDeliveryAdapter(getContext(),list);
        rec.setAdapter(adapterdelivery);
    }
}