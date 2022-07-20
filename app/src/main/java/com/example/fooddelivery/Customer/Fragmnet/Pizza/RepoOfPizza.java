package com.example.fooddelivery.Customer.Fragmnet.Pizza;

import androidx.annotation.NonNull;

import com.example.fooddelivery.pojo.Food;
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

public class RepoOfPizza {



    public Single<List<Food>> getPizza() {
        DatabaseReference fooodDatabase = FirebaseDatabase.getInstance().getReference("Food");
        ArrayList<Food> listOfFood = new ArrayList<>();
        return Single.create(new SingleOnSubscribe<List<Food>>() {
            @Override
            public void subscribe(@io.reactivex.rxjava3.annotations.NonNull SingleEmitter<List<Food>> emitter) throws Throwable {
                fooodDatabase.orderByChild("category").equalTo("Dessert").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                listOfFood.add(ds.getValue(Food.class));
                            }
                        }
                        emitter.onSuccess(listOfFood);


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
