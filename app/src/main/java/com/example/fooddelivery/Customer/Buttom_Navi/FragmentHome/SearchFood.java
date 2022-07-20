package com.example.fooddelivery.Customer.Buttom_Navi.FragmentHome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.Adapters.FoodAdapterSearch;
import com.example.fooddelivery.Customer.ViewCustomer;
import com.example.fooddelivery.R;
import com.example.fooddelivery.pojo.Food;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchFood extends AppCompatActivity {
    RecyclerView rec;
    ImageView back;
    FoodAdapterSearch adapter;
    SearchView search;
    ArrayList<Food> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food);
        getFindvieiw();
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
                startActivity(new Intent(getApplicationContext(), ViewCustomer.class));
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

        FirebaseDatabase.getInstance().getReference("Food").addListenerForSingleValueEvent(new ValueEventListener() {
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
        adapter = new FoodAdapterSearch(food, getApplicationContext());
        rec.setAdapter(adapter);
    }

    public void getFindvieiw() {
        rec = findViewById(R.id.menu_rec_customer);
        search = findViewById(R.id.search_menu_customer);
        back = findViewById(R.id.back_viewCustomer_search);
        rec.setLayoutManager(new LinearLayoutManager(this));
    }

}