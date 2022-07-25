package com.example.fooddelivery.Admin.AdminPannel.Fragmentpannel.DashboardFragment;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;

public class RepoDashBoardPannel {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");


    public Single<Integer> getNumberOfCustomers() {

        return Single.create(new SingleOnSubscribe<Integer>() {
            @Override
            public void subscribe(@io.reactivex.rxjava3.annotations.NonNull SingleEmitter<Integer> emitter) throws Throwable {
                ref.orderByChild("usertype").equalTo("Customer")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                emitter.onSuccess((int) snapshot.getChildrenCount());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                emitter.onError(error.toException());
                            }
                        });

            }
        });


    }


    public Single<Integer> getNumberOfDeliverys() {

        return Single.create(new SingleOnSubscribe<Integer>() {
            @Override
            public void subscribe(@io.reactivex.rxjava3.annotations.NonNull SingleEmitter<Integer> emitter) throws Throwable {
                ref.orderByChild("usertype").equalTo("Delivery")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                emitter.onSuccess((int) snapshot.getChildrenCount());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                emitter.onError(error.toException());
                            }
                        });

            }
        });


    }


    public Single<Integer> getNumberOfOrders() {

        return Single.create(new SingleOnSubscribe<Integer>() {
            @Override
            public void subscribe(@io.reactivex.rxjava3.annotations.NonNull SingleEmitter<Integer> emitter) throws Throwable {
                ref.orderByChild("statue").equalTo("Pending")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                emitter.onSuccess((int) snapshot.getChildrenCount());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                emitter.onError(error.toException());
                            }
                        });

            }
        });


    }

}
