package com.example.fooddelivery.Delivery.Registration;

import androidx.annotation.NonNull;

import com.example.fooddelivery.pojo.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;

public class RepoDelivery {


    public Single<Boolean> CreateEmail(String email, String password) {
        return Single.create(new SingleOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@io.reactivex.rxjava3.annotations.NonNull SingleEmitter<Boolean> emitter) throws Throwable {

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    emitter.onSuccess(true);
                                } else {
                                    emitter.onError(task.getException());
                                }
                            }
                        });
            }
        });
    }

    public Single<Boolean> Saving(User user) {
        return Single.create(new SingleOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@io.reactivex.rxjava3.annotations.NonNull SingleEmitter<Boolean> emitter) throws Throwable {
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference("Users");

                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                user.setId(uid);
                databaseReference.child(uid)
                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            emitter.onSuccess(true);

                        } else {
                            emitter.onError(task.getException());
                        }
                    }
                });
            }
        });

    }

}
