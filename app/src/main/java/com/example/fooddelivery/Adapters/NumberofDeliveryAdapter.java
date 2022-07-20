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

public class NumberofDeliveryAdapter extends RecyclerView.Adapter<NumberofDeliveryAdapter.ViewHolder> {
    ArrayList<User> listofUsers = new ArrayList<>();
    Context context;
    CourierId courierId;

    public NumberofDeliveryAdapter(Context context, ArrayList<User> listofUsers) {
        this.listofUsers = listofUsers;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_list_pannel, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = listofUsers.get(position);
        holder.updateUi(user);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                courierId.sendId(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listofUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, location, id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameofDelivery);
            location = itemView.findViewById(R.id.locationofDelivery);
            id = itemView.findViewById(R.id.idfDelivery);
        }

        public void updateUi(User user) {
            name.setText(user.getName());
            location.setText(user.getLocation());
            id.setText(user.getId());
        }
    }

    public interface CourierId {
        void sendId(User user);
    }

}
