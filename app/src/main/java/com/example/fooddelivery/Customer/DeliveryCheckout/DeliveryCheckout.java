package com.example.fooddelivery.Customer.DeliveryCheckout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fooddelivery.CartData;
import com.example.fooddelivery.Customer.ViewCustomer;
import com.example.fooddelivery.R;
import com.example.fooddelivery.pojo.CartItem;
import com.example.fooddelivery.pojo.Order;
import com.example.fooddelivery.pojo.OrderProductItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DeliveryCheckout extends AppCompatActivity {
    CartData cart;
    EditText city, area, streetname, buildingNumber, floorNumber;
    Button buttonContinue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_details);
        cart = new CartData(getApplicationContext());
        findviewbyIdd();
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadLocationToAdmin();
            }
        });
    }


    public void UploadLocationToAdmin() {
        String getCity, getArea, getstreetName, getbNumber, getfNumber, getFullAdress;
        ArrayList<OrderProductItem> orderProductItemArrayList = getFoodfromShopping();
        DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference("Orders");
        int price = getIntent().getIntExtra("Price", 0);
        String orderId = ordersRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).push().getKey();
        getCity = city.getText().toString();
        getArea = area.getText().toString();
        getstreetName = streetname.getText().toString();
        getbNumber = buildingNumber.getText().toString();
        getfNumber = floorNumber.getText().toString();

        if (getbNumber.isEmpty() || getCity.isEmpty() || getstreetName.isEmpty() || getfNumber.isEmpty() || getArea.isEmpty()) {
            Toast.makeText(this, "Fields are required", Toast.LENGTH_SHORT).show();
        } else {
            getFullAdress = getCity + "_" + getArea + "_" + getstreetName + "_" + getbNumber + "_" + getfNumber;
            Order order = new Order(orderId, getFullAdress, price, "Pending", orderProductItemArrayList);
            order.setUserUid(FirebaseAuth.getInstance().getCurrentUser().getUid());
            ordersRef.child(orderId)
                    .setValue(order).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(DeliveryCheckout.this, "Order Sent Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), ViewCustomer.class));
                } else {
                    Toast.makeText(DeliveryCheckout.this, "Order Sent Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void findviewbyIdd() {
        city = findViewById(R.id.city_location);
        area = findViewById(R.id.area_location);
        streetname = findViewById(R.id.streetname_location);
        buildingNumber = findViewById(R.id.buildingnumber_location);
        floorNumber = findViewById(R.id.floornumber_location);
        buttonContinue = findViewById(R.id.btn_continue);
    }

    public ArrayList<OrderProductItem> getFoodfromShopping() {
        ArrayList<OrderProductItem> orderProductItems = new ArrayList<>();
        ArrayList<CartItem> cartItems = cart.getCart();
        for (int i = 0; i < cartItems.size(); i++) {
            orderProductItems.add(new OrderProductItem(cartItems.get(i).getId(),
                    cartItems.get(i).getQuantity()));
        }
        return orderProductItems;
    }




}