package com.example.fooddelivery.Customer.Details;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddelivery.Adapters.DetailsAdapater;
import com.example.fooddelivery.CartData;
import com.example.fooddelivery.R;
import com.example.fooddelivery.pojo.Food;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityDetials extends AppCompatActivity {
    RecyclerView rec;
    DetailsAdapater adapter;
    CheckBox lettuce, pepper, olive;
    int lettuce_price = 5, pepper_price = 7, olive_price = 6;
    ImageView image;
    Button plus, minus, checkout;
    TextView txt_name, description, count, total_price;
    String getprice, getId;
    int total = 0, counter = 1, itemPrice;
    CartData cart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        cart = new CartData(getApplicationContext());
        bind();
        Intent intent = getIntent();
        getId = intent.getStringExtra("id");
        getFood();
        getFoodItem();
        checkBox();
        if (intent.getBooleanExtra("isDrink", false)) {
            lettuce.setVisibility(View.GONE);
            pepper.setVisibility(View.GONE);
            olive.setVisibility(View.GONE);
        }
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                updatePrice();


            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter == 1) return;
                counter = counter - 1;
                updatePrice();
            }
        });
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cart.isInCart(getId, getApplicationContext())) {
                    cart.remove(getId, getApplicationContext());
                    Toast.makeText(ActivityDetials.this, "Sent Food with Updated", Toast.LENGTH_SHORT).show();
                } else {
                    cart.addToCart(getId, getApplicationContext());
                    Toast.makeText(ActivityDetials.this, "Sent Food without Updated", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    @SuppressLint("SetTextI18n")
    private void updatePrice() {
        count.setText(counter + "");
        itemPrice = Integer.parseInt(getprice);
        total = itemPrice * counter;
        total_price.setText(total + "");
    }
    public void getFood() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Food");

        ArrayList<Food> list = new ArrayList<>();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("test", snapshot.exists() + "");
                Log.d("test", snapshot.getChildren() + "");

                for (DataSnapshot snapsh : snapshot.getChildren()) {
                    Log.d("test 2", snapsh.getValue(Food.class) + "");

                    list.add(snapsh.getValue(Food.class));
                }
                updateFood(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("cansel", error.getMessage() + "");

            }
        });
    }
    public void updateFood(ArrayList<Food> food) {
        adapter = new DetailsAdapater(getApplicationContext(), food);
        rec.setAdapter(adapter);
    }
    public void bind() {
        image = findViewById(R.id.img_details);
        plus = findViewById(R.id.button6_add);
        minus = findViewById(R.id.button7_minus);
        count = findViewById(R.id.textView20_count);
        checkout = findViewById(R.id.login_button);
        txt_name = findViewById(R.id.name_details);
        lettuce = findViewById(R.id.lettuce_check);
        pepper = findViewById(R.id.pepper_check);
        olive = findViewById(R.id.olive_check);
        description = findViewById(R.id.description_details);
        total_price = findViewById(R.id.total_detalis);
        rec = findViewById(R.id.re);
        rec.setLayoutManager(new LinearLayoutManager(this));
    }
    public void checkBox() {
        lettuce.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    total += lettuce_price;
                    total_price.setText(total + "");

                } else {
                    total -= lettuce_price;
                    total_price.setText(total + "");
                }
                getprice = String.valueOf(total);
            }
        });
        pepper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    total += pepper_price;
                    total_price.setText(total + "");
                } else {
                    total -= pepper_price;
                    total_price.setText(total + "");
                }
                getprice = String.valueOf(total);
            }
        });
        olive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    total += olive_price;
                    total_price.setText(total + "");
                } else {
                    total -= olive_price;
                    total_price.setText(total + "");
                }
                getprice = String.valueOf(total);
            }
        });
    }
    public void getFoodItem() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Food");

        ref.child(getId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Food food = snapshot.getValue(Food.class);
                        getprice = food.getPrice();
                        txt_name.setText(food.getName());
                        description.setText(food.getDecsription());
                        total = Integer.parseInt(food.getPrice());
                        total_price.setText(food.getPrice() + "");
                        Glide.with(getApplicationContext()).load(food.getPicUrl()).into(image);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

}