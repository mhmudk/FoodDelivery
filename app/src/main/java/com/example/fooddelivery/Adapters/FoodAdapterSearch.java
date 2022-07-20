package com.example.fooddelivery.Adapters;

import android.content.Context;
import android.content.Intent;
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
import com.example.fooddelivery.CartDatafavourite;
import com.example.fooddelivery.Customer.Details.ActivityDetials;
import com.example.fooddelivery.R;
import com.example.fooddelivery.pojo.Food;

import java.util.ArrayList;

public class FoodAdapterSearch extends RecyclerView.Adapter<FoodAdapterSearch.ViewHoder> {
    ArrayList<Food> listModel;
    Context mContext;
    CartData cartData;
    CartDatafavourite cartDatafa ;
    public FoodAdapterSearch(ArrayList<Food> listModel, Context mContext) {
        this.listModel = listModel;
        this.mContext = mContext;
        cartData = new CartData(mContext);
        cartDatafa = new CartDatafavourite(mContext);
    }

    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.burger_customer_list, parent, false);
        return new ViewHoder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        Food food = listModel.get(position);
        holder.updateUI(food);

        holder.add.setOnClickListener(view -> {
            onAddItem(holder, food);

        });

        holder.favourite.setOnClickListener(view -> {
            holder.favourite.setSelected(true);
            if (cartDatafa.isInCart(food.getId(), mContext)) {
                cartDatafa.remove(food.getId(), mContext);
                Toast.makeText(mContext, "Item Removed from Favourite", Toast.LENGTH_SHORT).show();
            } else {
                cartDatafa.addToCart(food.getId(), mContext);

                Toast.makeText(mContext, "Item Add to Favourite", Toast.LENGTH_SHORT).show();
            }
            holder.updateButtonFavourite(food);

        });
    }

    private void onAddItem(ViewHoder holder, Food food) {
        if (cartData.isInCart(food.getId(), mContext)) {
            cartData.remove(food.getId(), mContext);
            Toast.makeText(mContext, "Item Removed", Toast.LENGTH_SHORT).show();
        } else {
            cartData.addToCart(food.getId(), mContext);
            Toast.makeText(mContext, "Add to Card", Toast.LENGTH_SHORT).show();
        }
        holder.updateButton(food);
    }
    public void filterlist(ArrayList<Food> newlist){
        this.listModel = newlist;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return listModel.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        View myview;
        ImageView img,favourite;
        TextView name, price;
        Button add;


        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            myview = itemView;
            img = itemView.findViewById(R.id.imageView8);
            name = itemView.findViewById(R.id.textView11);
            price = itemView.findViewById(R.id.textView14);
            add = itemView.findViewById(R.id.button5);
            favourite = itemView.findViewById(R.id.image_fav);

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(mContext, ActivityDetials.class);
               intent.putExtra("id", listModel.get(getAdapterPosition()).getId());
                mContext.startActivity(intent);
            });

        }

        public void updateUI(Food food) {
            name.setText(food.getName());
            price.setText(food.getPrice() + " EGP");
            Glide.with(mContext).load(food.getPicUrl()).into(img);
            updateButton(food);
        }


        public void updateButton(Food food) {
            if (cartData.isInCart(food.getId(), mContext)) {
                add.setText("-");
            } else {
                add.setText("+ Order");

            }
        }

        public void updateButtonFavourite(Food food) {
            if (cartDatafa.isInCart(food.getId(),mContext)) {
                favourite.setImageResource(R.drawable.ic_heart);
            } else {

                favourite.setImageResource(R.drawable.ic_heart_1);
            }
        }


    }

}