package com.example.fooddelivery.Delivery.ProfileDelivery.Orders;

import com.example.fooddelivery.pojo.Order;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;

public class RepoOFOrdersDelivery {
    public Single<ArrayList<Order>> getOrders() {
        DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference("Orders");

        ;
        ArrayList<Order> arrayList = new ArrayList<>();
        return Single.create(new SingleOnSubscribe<ArrayList<Order>>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<ArrayList<Order>> emitter) throws Throwable {

                ordersRef.orderByChild("courierId").equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                for (DataSnapshot snap : snapshot.getChildren()) {
                                    arrayList.add(snap.getValue(Order.class));

                                }
                                emitter.onSuccess(arrayList);
                            }

                            @Override
                            public void onCancelled(DatabaseError error) {
                                emitter.onError(error.toException());
                            }
                        });


            }
        });

    }
}
