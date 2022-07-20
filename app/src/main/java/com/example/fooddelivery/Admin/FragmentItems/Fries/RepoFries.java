package com.example.fooddelivery.Admin.FragmentItems.Fries;


import android.net.Uri;

import com.example.fooddelivery.pojo.Food;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;

public class RepoFries {
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Food");
    public Single<Boolean> push(Food food, Uri filePath) {
       return Single.create(new SingleOnSubscribe<Boolean>() {
           @Override
           public void subscribe(@NonNull SingleEmitter<Boolean> emitter) throws Throwable {
               String uid = ref.push().getKey();
               food.setId(uid);
               photo(food, filePath);
           }
       });

    }

    public Single<Boolean> photo(Food food, Uri uri) {
        return Single.create(new SingleOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<Boolean> emitter) throws Throwable {
                String refId = ref.push().getKey();
                food.setId(refId);
                assert refId != null;

                StorageReference foodRef = FirebaseStorage.getInstance()
                        .getReference("food").child(refId);
                foodRef.putFile(uri)
                        .addOnSuccessListener(taskSnapshot ->
                                foodRef.getDownloadUrl().
                                        addOnSuccessListener(uri1 -> {
                                            food.setPicUrl(uri1.toString());
                                            pushToDatabase(food);
                                        })).addOnFailureListener(exception -> {
                    emitter.onError(exception);
                });
            }
        });

    }

    public  Single<Boolean> pushToDatabase(Food food) {
       return Single.create(new SingleOnSubscribe<Boolean>() {
           @Override
           public void subscribe(@NonNull SingleEmitter<Boolean> emitter) throws Throwable {
               ref.child(food.getId()).setValue(food).addOnCompleteListener(task -> {
                   if (task.isSuccessful()) {
                 emitter.onSuccess(true);
                   } else {
                       emitter.onError(task.getException());
                   }
               });
           }
       }) ;

    }


}
