package com.example.fooddelivery.Admin.AdminPannel.Fragmentpannel.DashboardFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.Adapters.MenuAdapater;
import com.example.fooddelivery.Admin.AdminPannel.AdminPannel;
import com.example.fooddelivery.R;
import com.example.fooddelivery.pojo.Food;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FoodItem extends AppCompatActivity {
    RecyclerView rec;
    MenuAdapater adapter;
    SearchView search;
    ImageView back ;
    ArrayList<Food> list = new ArrayList<>();
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Food");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item);
        rec = findViewById(R.id.menu_rec);
        back = findViewById(R.id.back_admint_todash_search);
        search = findViewById(R.id.search_menu);
        rec.setLayoutManager(new LinearLayoutManager(this));
        getFood();
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (list.contains(query)) {

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
    back.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(), AdminPannel.class));

        }
    });
    }

    public void filter(String newText) {
        ArrayList<Food> listfoodsearch = new ArrayList<>();
        for (Food food : list) {
            if (food.getName().toLowerCase().contains(newText.toLowerCase())) {
                listfoodsearch.add(food);
            }

        }
        adapter.filterlist(listfoodsearch);
    }

    public void getFood() {

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapsh : snapshot.getChildren()) {

                    list.add(snapsh.getValue(Food.class));
                    updateFood(list);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void updateFood(ArrayList<Food> food) {
        adapter = new MenuAdapater(getApplicationContext(), food);
        rec.setAdapter(adapter);
    }
}