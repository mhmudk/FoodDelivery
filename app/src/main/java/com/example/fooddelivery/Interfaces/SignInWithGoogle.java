package com.example.fooddelivery.Interfaces;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.UserProfileChangeRequest;

public interface SignInWithGoogle {
    void AuthWithGoogle(GoogleSignInAccount account);
    void handleSignInWithGoogle(Task<GoogleSignInAccount> completedTask);
    void SetValueWithGoogle(UserProfileChangeRequest.Builder request);

}
