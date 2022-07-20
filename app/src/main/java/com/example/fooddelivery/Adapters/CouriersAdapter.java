package com.example.fooddelivery.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.R;
import com.example.fooddelivery.pojo.User;

import java.util.ArrayList;

public class CouriersAdapter extends RecyclerView.Adapter<CouriersAdapter.ViewHolder> {
    ArrayList<User> arrayListCouriers;
    Context context;
    UserClick userClick;

    public CouriersAdapter(ArrayList<User> arrayListCouriers, Context context, UserClick userClick) {
        this.arrayListCouriers = arrayListCouriers;
        this.userClick = userClick;
        this.context = context;
    }

    public interface UserClick {
        void onUserClick(User user);
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_list_pannel_courier, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = arrayListCouriers.get(position);
        holder.updateUi(user);
        holder.itemView.setOnClickListener(v-> {
            userClick.onUserClick(user);
        });
    }

    @Override
    public int getItemCount() {
        return arrayListCouriers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, location, id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameofDelivery_cuorier);
            location = itemView.findViewById(R.id.locationofDelivery_cuorier);
            id = itemView.findViewById(R.id.idfDelivery_cuorier);
        }

        public void updateUi(User user) {
            name.setText(user.getName());
            location.setText(user.getLocation());
            id.setText(user.getId());
        }
    }

}
