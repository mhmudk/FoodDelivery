package com.example.fooddelivery.Login.LogInRegistration;

import com.google.firebase.auth.FirebaseAuth;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;

public class LoginRepo {

    public Single<Boolean> Login(String email, String passowrd) {
     return Single.create(new SingleOnSubscribe<Boolean>() {
         @Override
         public void subscribe(@NonNull SingleEmitter<Boolean> emitter) throws Throwable {
             FirebaseAuth.getInstance().signInWithEmailAndPassword(email, passowrd).
                     addOnCompleteListener(task -> {
                         if (task.isSuccessful()) {
                           emitter.onSuccess(true);
                         } else {
                             emitter.onError(task.getException());
                         }

                     });
         }
     });

    }
}
