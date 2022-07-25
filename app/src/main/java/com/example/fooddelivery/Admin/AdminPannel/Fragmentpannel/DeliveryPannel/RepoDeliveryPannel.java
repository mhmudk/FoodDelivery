package com.example.fooddelivery.Admin.AdminPannel.Fragmentpannel.DeliveryPannel;

import androidx.annotation.NonNull;

import com.example.fooddelivery.pojo.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;

public class RepoDeliveryPannel {
    DatabaseReference deliveryRef = FirebaseDatabase.getInstance().getReference("Users");

    public Single<ArrayList<User>> getDeliveryData() {
        ArrayList<User> listUser = new ArrayList<>();
        return Single.create(new SingleOnSubscribe<ArrayList<User>>() {
            @Override
            public void subscribe(@io.reactivex.rxjava3.annotations.NonNull SingleEmitter<ArrayList<User>> emitter) throws Throwable {
                deliveryRef.orderByChild("usertype").equalTo("Delivery")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
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
