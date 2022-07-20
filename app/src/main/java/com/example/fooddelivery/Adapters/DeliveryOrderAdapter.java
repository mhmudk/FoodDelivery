package com.example.fooddelivery.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.Interfaces.IDoneDeliverey;
import com.example.fooddelivery.R;
import com.example.fooddelivery.pojo.Order;
import com.example.fooddelivery.pojo.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DeliveryOrderAdapter extends RecyclerView.Adapter<DeliveryOrderAdapter.ViewHolder> {
    ArrayList<Order> orderArrayList;
    Context context;
    IDoneDeliverey iDoneDeliverey;

    public DeliveryOrderAdapter(ArrayList<Order> orderArrayList, Context context, IDoneDeliverey iDoneDeliverey) {
        this.orderArrayList = orderArrayList;

        this.context = context;
        this.iDoneDeliverey = iDoneDeliverey;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.deliveryorders_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = orderArrayList.get(position);
        holder.updateUi(order);
        String id  = orderArrayList.get(position).getOrderId();

        holder.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FirebaseDatabase.getInstance().getReference("Orders")
//               .child(id)
//              .child("statue").setValue("delivered");
            iDoneDeliverey.done(id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderArrayList.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, location, price, phone, pieces;
        Button done;
        ImageView meal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_location);
            location = itemView.findViewById(R.id.location_delivery);
            pieces = itemView.findViewById(R.id.pieces_delivery);
            price = itemView.findViewById(R.id.price_delivery);
            phone = itemView.findViewById(R.id.phone_location);
            done = itemView.findViewById(R.id.done_delivery);
            meal = itemView.findViewById(R.id.image_location);
        }

        private void updateUi(Order order) {
            getUsername(order.getUserUid());
            location.setText(order.getAdress());
            price.setText(order.getTotalPrice() + "EGP");
            pieces.setText(order.getQuantity() + "Pieces");

        }

        private void getUsername(String userUid) {
            if (userUid.isEmpty()) return;
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
            ref.child(userUid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User user = snapshot.getValue(User.class);
                    name.setText(user.getName());
                    phone.setText(user.getPhone());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
//هو حقته يجيب كل الاوردرز ال انا موصلهلاله
//هندسه هو حقته دلوقتي يجيب كل الاوردر ال باسمه فهمني ؟ اه مش عارف لي مش راضي يظهر  الاوردر هو ممكن يكون من دي بص كدا
}
