package com.example.fooddelivery.Customer.Buttom_Navi.FragmentHome.Norfication;

import androidx.annotation.NonNull;

import com.example.fooddelivery.pojo.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;

public class RepoNotification {


    public Single<List<Order>> getNotificationOrdres(){
        ArrayList<Order> orderArrayList = new ArrayList<>();
return Single.create(new SingleOnSubscribe<List<Order>>() {
    @Override
    public void subscribe(@io.reactivex.rxjava3.annotations.NonNull SingleEmitter<List<Order>> emitter) throws Throwable {
        FirebaseDatabase.getInstance().getReference("Orders")
                .orderByChild("statue").equalTo("delivered")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){

                        for(DataSnapshot snap :snapshot.getChildren()){
                            orderArrayList.add(snap.getValue(Order.class));
                        }
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
}
