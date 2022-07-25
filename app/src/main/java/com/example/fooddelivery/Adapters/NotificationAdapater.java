package com.example.fooddelivery.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.R;
import com.example.fooddelivery.pojo.Order;

import java.util.List;

public class NotificationAdapater extends RecyclerView.Adapter<NotificationAdapater.ViewHolder> {
    List<Order> list;
    Context context;

    public NotificationAdapater(Context context, List<Order> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.notification_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order notification = list.get(position);
        holder.updateFood(notification);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView statue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            statue = itemView.findViewById(R.id.notification_delivered);

        }

        public void updateFood(Order food) {

            statue.setText(food.getStatue());

        }
    }

}
