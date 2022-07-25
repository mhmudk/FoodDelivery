package com.example.fooddelivery.Admin.AdminPannel.Fragmentpannel.OrdersPannel;

import androidx.annotation.NonNull;

import com.example.fooddelivery.pojo.Order;
import com.example.fooddelivery.pojo.OrderProductItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;


public class RepoOrdersPannel {
    DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference("Orders");

    public Single<ArrayList<Order>> getOrdersFromFirebase() {
        ArrayList<Order> orderArrayList = new ArrayList<>();
        return Single.create(new SingleOnSubscribe<ArrayList<Order>>() {
            @Override
            public void subscribe(@io.reactivex.rxjava3.annotations.NonNull SingleEmitter<ArrayList<Order>> emitter) throws Throwable {
                orderRef.orderByChild("statue").equalTo("Pending")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot ds : snapshot.getChildren()) {
                                    Order order = ds.getValue(Order.class);

                                    getExtra(order, ds);
                                    orderArrayList.add(order);

                                }
                                emitter.onSuccess(orderArrayList);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                emitter.onError(error.toException());
                            }
                        });
            }
        });

    }

    private void getExtra(Order order, DataSnapshot datatsnap) {
        if (datatsnap.child("listProducts").exists()) {
            order.getListProducts().clear();
            for (DataSnapshot prods : datatsnap.child("listProducts").getChildren()) {
                order.getListProducts().add(prods.getValue(OrderProductItem.class));
            }
        }
    }


}
