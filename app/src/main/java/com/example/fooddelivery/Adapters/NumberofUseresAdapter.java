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

public class NumberofUseresAdapter extends RecyclerView.Adapter<NumberofUseresAdapter.ViewHolder> {
    ArrayList<User> listofUsers = new ArrayList<>();
    Context context;

    public NumberofUseresAdapter(Context context, ArrayList<User> listofUsers) {
        this.listofUsers = listofUsers;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_list_pannel, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = listofUsers.get(position);
        holder.updateUi(user);
    }

    @Override
    public int getItemCount() {
        return listofUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, location, id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.user_admin);
            location = itemView.findViewById(R.id.location_admin_user);
            id = itemView.findViewById(R.id.id_admin_users);
        }

        public void updateUi(User user) {
            name.setText(user.getName());
            location.setText(user.getLocation());
            id.setText(user.getId());
        }
    }
}
