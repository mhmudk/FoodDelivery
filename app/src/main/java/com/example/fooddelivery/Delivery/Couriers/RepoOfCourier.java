package com.example.fooddelivery.Delivery.Couriers;

import androidx.annotation.NonNull;

import com.example.fooddelivery.pojo.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;

public class RepoOfCourier {

    public Single<List<User>> getDeliveryData() {
        DatabaseReference deliveryRef = FirebaseDatabase.getInstance().getReference("Users");
        ArrayList<User> listUser = new ArrayList<>();
        return Single.create(new SingleOnSubscribe<List<User>>() {
            @Override
            public void subscribe(@io.reactivex.rxjava3.annotations.NonNull SingleEmitter<List<User>> emitter) throws Throwable {
                deliveryRef.orderByChild("usertype").equalTo("Delivery").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            listUser.add(ds.getValue(User.class));
                        }
                        emitter.onSuccess(listUser);

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
