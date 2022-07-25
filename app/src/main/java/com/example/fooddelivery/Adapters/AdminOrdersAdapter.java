package com.example.fooddelivery.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.Admin.AdminPannel.Fragmentpannel.DashboardFragment.IOnAdapterClickListener;
import com.example.fooddelivery.R;
import com.example.fooddelivery.pojo.Order;
import com.example.fooddelivery.pojo.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminOrdersAdapter extends RecyclerView.Adapter<AdminOrdersAdapter.ViewHolder> {
    ArrayList<Order> arrayListorders;
    IOnAdapterClickListener listener;

    public AdminOrdersAdapter(ArrayList<Order> arrayListorders, IOnAdapterClickListener listener) {
        this.arrayListorders = arrayListorders;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_admin, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Order order = arrayListorders.get(position);
        holder.updateUi(order);

        holder.accept.setOnClickListener(view -> {
            listener.onAcceptClickListener(order.getOrderId());

        });

        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRejectClickListener(holder.getBindingAdapterPosition());
            }
        });

    }


    @Override
    public int getItemCount() {
        return arrayListorders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, count, total, location, phonenumber;
        Button accept, reject;


        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_oforders);
            location = itemView.findViewById(R.id.location_admin);
            phonenumber = itemView.findViewById(R.id.phone_location_admin);
            count = itemView.findViewById(R.id.piecese_oforders);
            total = itemView.findViewById(R.id.total_oforders);
            accept = itemView.findViewById(R.id.accept_admin);
            reject = itemView.findViewById(R.id.admin_reject);

        }

        public void updateUi(Order order) {
            count.setText(order.getProductsCount() + " ");
            total.setText(order.getTotalPrice() + "");

            getUserName(order.getUserUid());
        }


        private void getUserName(String userUid) {
            if (userUid.isEmpty()) return;
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
            databaseReference.child(userUid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        User user = snapshot.getValue(User.class);
                        name.setText(user.getName());
                        phonenumber.setText(user.getPhone() + "");
                        location.setText(user.getLocation() + "");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
    }
}
