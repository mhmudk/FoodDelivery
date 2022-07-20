package com.example.fooddelivery.Admin.FragmentItems.Burger;


import android.net.Uri;

import com.example.fooddelivery.pojo.Food;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class RepoBurger {
    BurgerInterface binterface;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Food");

    public RepoBurger(BurgerInterface binterface) {
        this.binterface = binterface;
    }

    public void push(Food food, Uri filePath) {
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
                                    pushToDatabase(food);
                                })).addOnFailureListener(exception -> {
            binterface.Faild(exception.getMessage());
        });
    }

    private void pushToDatabase(Food food) {
        ref.child(food.getId()).setValue(food).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                binterface.Successed(food);
            } else {
                binterface.Faild(task.getException().getMessage());
            }
        });
    }

    public interface BurgerInterface {
        void Successed(Food food);

        void Faild(String error);
    }
}
