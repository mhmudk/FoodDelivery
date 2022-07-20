package com.example.fooddelivery.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddelivery.CartData;
import com.example.fooddelivery.R;
import com.example.fooddelivery.pojo.Food;

import java.util.ArrayList;

public class DetailsAdapater extends RecyclerView.Adapter<DetailsAdapater.ViewHolder>  {
    ArrayList<Food> list;
    Context context;
    CartData cartData;
    public DetailsAdapater(Context context, ArrayList<Food> list) {
        this.list = list;
        this.context = context;
        cartData = new CartData(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.details_list, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food food = list.get(position);
        holder.updateFood(food);
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartData.isInCart(food.getId(),context)){
                    cartData.remove(food.getId(),context);
                    Toast.makeText(context, "Item Add to Cart", Toast.LENGTH_SHORT).show();
                    holder.UpdateUi(food);
                }else{
                    cartData.addToCart(food.getId(),context);
                    Toast.makeText(context, "Item Removed to Cart", Toast.LENGTH_SHORT).show();

                    holder.UpdateUi(food);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name, price;
Button add ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView9);
            name = itemView.findViewById(R.id.textView17);
            price = itemView.findViewById(R.id.textView18);
            add = itemView.findViewById(R.id.button6);

        }

        public void updateFood(Food food) {
            Glide.with(itemView).load(food.getPicUrl()).into(img);
            name.setText(food.getName());
            price.setText(food.getPrice()+"");
        }
        public void UpdateUi(Food food){
            if(cartData.isInCart(food.getId(),context)){
                add.setText("-");
            }else{
                add.setText("+");
            }
        }
    }

}
