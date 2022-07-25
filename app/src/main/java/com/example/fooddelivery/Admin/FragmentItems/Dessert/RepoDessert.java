package com.example.fooddelivery.Admin.FragmentItems.Dessert;

import android.net.Uri;

import com.example.fooddelivery.pojo.Food;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class RepoDessert {
    DessertInterface dinterface;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Food");
    StorageReference foodRef = FirebaseStorage.getInstance().getReference("food");

    public RepoDessert(DessertInterface dinterface) {
        this.dinterface = dinterface;
    }

    public void startPushing(Food food, Uri filePath) {
        String uid = ref.push().getKey();
        food.setId(uid);
        photo(food, filePath);
    }


    public void photo(Food food, Uri uri) {
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
                                    uploadToDataBase(food);
                                })).addOnFailureListener(exception -> {
                    dinterface.Faild(exception.getMessage());
                });
    }


    public void uploadToDataBase(Food food) {

        ref.child(food.getId()).setValue(food).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                dinterface.Successed();
            } else {
                dinterface.Faild(task.getException().getMessage());
            }
        });
    }

    public interface DessertInterface {
        void Successed();

        void Faild(String error);
    }
}
