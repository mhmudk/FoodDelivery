package com.example.fooddelivery.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.fooddelivery.CartDatafavourite;
import com.example.fooddelivery.R;
import com.example.fooddelivery.pojo.Food;

import java.util.ArrayList;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {
    ArrayList<Food> arrayList;
    Context mContext;

    CartDatafavourite cartDatafa;

    public FavouriteAdapter(ArrayList<Food> arrayList, Context mContext) {
        this.arrayList = arrayList;
        this.mContext = mContext;

        cartDatafa = new CartDatafavourite(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_list, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Food food = arrayList.get(position);
        holder.updateUiFavourite(food);
        holder.cansel.setOnClickListener(view -> {
            holder.cansel.setImageResource(R.drawable.ic_heart_1);
            arrayList.remove(position);
            notifyItemRemoved(position);
            cartDatafa.remove(food.getId(), mContext);
            Toast.makeText(mContext, food.getName() + " Removed", Toast.LENGTH_SHORT).show();

        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img, cansel;
        TextView name, price,description;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView9_fav);
            name = itemView.findViewById(R.id.textView17_fav);
            price = itemView.findViewById(R.id.textView18);
            cansel = itemView.findViewById(R.id.cansel_fav);
            description = itemView.findViewById(R.id.description_list_fav);

        }

        public void updateUiFavourite(Food food) {
            name.setText(food.getName());
            price.setText(food.getPrice() +"");
            description.setText(food.getDecsription());
            Glide.with(mContext).load(food.getPicUrl()).diskCacheStrategy(DiskCacheStrategy.DATA).into(img);

        }

    }

}
