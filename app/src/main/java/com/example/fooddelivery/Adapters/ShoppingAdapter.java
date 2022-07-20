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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.fooddelivery.CartData;
import com.example.fooddelivery.R;
import com.example.fooddelivery.pojo.CartItem;
import com.example.fooddelivery.pojo.Food;

import java.util.ArrayList;

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ViewHolder>  {
    ArrayList<Food> arrayList;
    Context mContext;
    CartData cartData;
    onChangeMade changeMade;

    public ShoppingAdapter(ArrayList<Food> arrayList, Context mContext, onChangeMade changeMade) {
        this.arrayList = arrayList;
        this.mContext = mContext;
        this.changeMade = changeMade;
        cartData = new CartData(mContext);

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_list, parent, false);
        return new ViewHolder(v);
    }



    public interface onChangeMade {
        void changeMade();
        void CheckSize(ArrayList<Food>array);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food food = arrayList.get(position);



        holder.cansel.setOnClickListener(view -> {
   changeMade.CheckSize(arrayList);
            arrayList.remove(position);
            notifyItemRemoved(position);
            cartData.remove(food.getId(), mContext);
            changeMade.changeMade();
            Toast.makeText(mContext, food.getName() + " Removed", Toast.LENGTH_SHORT).show();
        });

        holder.updateUI(food);
    }

    private void updateQuantity(String id, int i) {
        cartData.updateQuantity(id, i);
        changeMade.changeMade();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name, price, count_item;
        Button add_shopping, minus_shopping;
        ImageView cansel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView9);
            name = itemView.findViewById(R.id.textView17);
            cansel = itemView.findViewById(R.id.imageView);
            count_item = itemView.findViewById(R.id.count_ofitem);
            price = itemView.findViewById(R.id.textView18);
            add_shopping = itemView.findViewById(R.id.button6);
      //      minus_shopping = itemView.findViewById(R.id.button7);
        }

        public void updateUI(Food food) {
            name.setText(food.getName());
            price.setText(food.getPrice() +"");
            Glide.with(mContext).load(food.getPicUrl()).diskCacheStrategy(DiskCacheStrategy.DATA).into(img);
            CartItem cartItem = cartData.getCartObject(food.getId());
            if(cartItem != null) {
                count_item.setText(cartItem.getQuantity() + "");
            }
        }


    }

}
