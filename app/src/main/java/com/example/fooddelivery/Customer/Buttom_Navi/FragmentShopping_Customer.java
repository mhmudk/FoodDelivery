package com.example.fooddelivery.Customer.Buttom_Navi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.Adapters.ShoppingAdapter;
import com.example.fooddelivery.CartData;
import com.example.fooddelivery.Customer.DeliveryCheckout.DeliveryCheckout;
import com.example.fooddelivery.Customer.ViewCustomer;
import com.example.fooddelivery.R;
import com.example.fooddelivery.pojo.CartItem;
import com.example.fooddelivery.pojo.Food;
import com.example.fooddelivery.pojo.Order;
import com.example.fooddelivery.pojo.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentShopping_Customer extends Fragment implements ShoppingAdapter.onChangeMade {
    RecyclerView rec;
    ShoppingAdapter shop;
    DatabaseReference foodDatabase = FirebaseDatabase.getInstance().getReference("Food");

    TextView textPrice, itemseleceted;
    private ArrayList<Food> listFood;
    CartData cartData;
    User user;
    Order order;
    Button buy;
    LinearLayout maincart, secondcart;

    ImageView back_viewcustomer;

    public FragmentShopping_Customer() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_shopping__customer, container, false);
        findviewByid(v);
        rec.setLayoutManager(new LinearLayoutManager(getContext()));
        cartData = new CartData(getContext());
        user = new User();
        order = new Order();
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int price = getTotalPrice();
                Intent intent = new Intent(getContext(), DeliveryCheckout.class);
                intent.putExtra("Price", price);
                startActivity(intent);
            }
        });
        back_viewcustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getContext(), ViewCustomer.class));
            }
        });
        getCartData();
        return v;
    }

    private void getCartData() {

        ArrayList<CartItem> listIds = cartData.getCart();
        if (listIds.size() == 0) {
            maincart.setVisibility(View.GONE);
            secondcart.setVisibility(View.VISIBLE);

        }
        listFood = new ArrayList<>();


        for (int i = 0; i < listIds.size(); i++) {
            String id = listIds.get(i).getId();
            int finalI = i;
            foodDatabase.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Food food = snapshot.getValue(Food.class);
                    if (food != null) {
                        listFood.add(food);
                    }
                    // Check if this is the last object
                    // Because the firebase works on a diffrent thread
                    if (finalI + 1 == listFood.size()) {
                        updateRecycler(listFood);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
    }

    private void updateRecycler(ArrayList<Food> listFood) {
        shop = new ShoppingAdapter(listFood, getContext(), this);
        rec.setAdapter(shop);

        changeMade();
    }

    @Override
    public void changeMade() {
        int totalItems = getTotalItems();
        int totalPrice = getTotalPrice();

        itemseleceted.setText(totalItems + "");
        textPrice.setText(totalPrice + "");

    }

    @Override
    public void CheckSize(ArrayList<Food> array) {
        if (array.size() == 0) {
            maincart.setVisibility(View.GONE);
            secondcart.setVisibility(View.VISIBLE);
        }
    }

    private int getTotalPrice() {
        ArrayList<CartItem> listItems = cartData.getCart();
        int money = 0;


        for (int i = 0; i < listItems.size(); i++) {
            Food food = getFood(listItems.get(i).getId());
            if (food != null) {
                money += (Integer.parseInt(food.getPrice()) * listItems.get(i).getQuantity() + 5);
            }
        }
        Bundle bundle = new Bundle();
        bundle.putString("price", String.valueOf(money));

        return money;
    }

    private Food getFood(String id) {
        for (Food food : listFood) {
            if (food.getId().equals(id)) return food;
        }
        return null;
    }

    private int getTotalItems() {
        ArrayList<CartItem> listItems = cartData.getCart();
        int total = 0;
        for (int i = 0; i < listItems.size(); i++) {
            total = total + listItems.get(i).getQuantity();
        }

        return total;
    }

    public void findviewByid(View v) {
        rec = v.findViewById(R.id.recyclerView_shopping);
        textPrice = v.findViewById(R.id.deliverycost_total);
        itemseleceted = v.findViewById(R.id.textView16);
        buy = v.findViewById(R.id.button2);
        maincart = v.findViewById(R.id.main_cart);
        secondcart = v.findViewById(R.id.second_cart);
        back_viewcustomer = v.findViewById(R.id.back_viewCustomer);
    }


}