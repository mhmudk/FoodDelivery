package com.example.fooddelivery.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddelivery.R;
import com.example.fooddelivery.pojo.Food;

import java.util.ArrayList;

public class MenuAdapater extends RecyclerView.Adapter<MenuAdapater.ViewHolder>  {
    ArrayList<Food> list;
    Context context;

    public MenuAdapater(Context context, ArrayList<Food> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.menu_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food food = list.get(position);
        holder.updateFood(food);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
public void filterlist(ArrayList<Food> newlist){
        this.list = newlist;
        notifyDataSetChanged();
}
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView9_menu);
            name = itemView.findViewById(R.id.textView17_menu);
            price = itemView.findViewById(R.id.price_menu);
        }

        public void updateFood(Food food) {
            Glide.with(itemView).load(food.getPicUrl()).into(img);
            name.setText(food.getName());
            price.setText(food.getPrice()+"");
        }
    }

}
