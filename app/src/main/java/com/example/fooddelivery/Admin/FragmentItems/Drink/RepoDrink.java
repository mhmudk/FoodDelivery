package com.example.fooddelivery.Admin.FragmentItems.Drink;

import android.net.Uri;

import com.example.fooddelivery.pojo.Food;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class RepoDrink {
    DrinktInterface drinktInterface;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Food");
    StorageReference store =  FirebaseStorage.getInstance().getReference("food");
    public RepoDrink(DrinktInterface drinktInterface) {
        this.drinktInterface = drinktInterface;
    }
   public void StartPushing(Food food, Uri filePath){
       String uid = ref.push().getKey();
       food.setId(uid);
       photo(food,filePath);
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
            drinktInterface.Faild(exception.getMessage());
        });
    }

    public void uploadToDataBase(Food food) {

        ref.child(food.getId()).setValue(food).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                drinktInterface.Successed();
            } else {
                drinktInterface.Faild(task.getException().getMessage());
            }
        });
    }


    public interface DrinktInterface {
        void Successed();

        void Faild(String error);
    }
}
