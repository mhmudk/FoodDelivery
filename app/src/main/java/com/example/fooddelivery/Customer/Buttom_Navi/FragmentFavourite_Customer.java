package com.example.fooddelivery.Customer.Buttom_Navi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.Adapters.FavouriteAdapter;
import com.example.fooddelivery.CartDatafavourite;
import com.example.fooddelivery.R;
import com.example.fooddelivery.pojo.CartItem;
import com.example.fooddelivery.pojo.Food;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class FragmentFavourite_Customer extends Fragment {
    TextView text_empty, findfood;

    DatabaseReference foodDatabase = FirebaseDatabase.getInstance().getReference("Food");
    FavouriteAdapter adFavourite;

    RecyclerView rec;
    LinearLayout empty_img;
    CartDatafavourite cartDatafa;

    public FragmentFavourite_Customer() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favourite, container, false);
        findViewbyIdd(v);

        rec.setLayoutManager(new LinearLayoutManager(getContext()));
        rec.setHasFixedSize(true);
        cartDatafa = new CartDatafavourite(getContext());
        getItemFavourite();
        return v;
    }

    public void getItemFavourite() {

        ArrayList<CartItem> listIds = cartDatafa.getCart();
        ArrayList<Food> listOfitem = new ArrayList<>();
        if (listIds.size() == 0) {
            rec.setVisibility(View.GONE);
            empty_img.setVisibility(View.VISIBLE);
        }
        for (int i = 0; i < listIds.size(); i++) {
            int finali = i;
            String id = listIds.get(i).getId();
            foodDatabase.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Food food = snapshot.getValue(Food.class);
                    if (food != null) {
                        listOfitem.add(food);
                    }
                    if (finali + 1 == listIds.size()) {
                        updateRecycler(listOfitem);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void updateRecycler(ArrayList<Food> listOfitem) {
        adFavourite = new FavouriteAdapter(listOfitem, getContext());
        rec.setAdapter(adFavourite);


    }

    public void findViewbyIdd(View v) {
        rec = v.findViewById(R.id.rec_fav);
        text_empty = v.findViewById(R.id.text_emptyfave);
        findfood = v.findViewById(R.id.finditem_fav);

        empty_img = v.findViewById(R.id.favouritevisibble);
    }


}