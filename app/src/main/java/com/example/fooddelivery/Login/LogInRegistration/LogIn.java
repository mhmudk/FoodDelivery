package com.example.fooddelivery.Login.LogInRegistration;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.fooddelivery.Customer.ViewCustomer;
import com.example.fooddelivery.Interfaces.SignInWithGoogle;
import com.example.fooddelivery.Login.ForgetPassword;
import com.example.fooddelivery.Manager.CreateAccount;
import com.example.fooddelivery.R;
import com.example.fooddelivery.pojo.LoginViewFatory;
import com.example.fooddelivery.pojo.User;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LogIn extends AppCompatActivity implements SignInWithGoogle {
    EditText memail, mpassword;
    TextView registeration_email, forget_password, signup, emailofsign;

    LoginViewModel viewmodel;
    FirebaseAuth fauth;
    GoogleSignInClient mGoogleSignInClient;
    Button google_btn, facebook_btn, login;
    CallbackManager mCallbackManager;
    private ActivityResultLauncher<Intent> launcher;
    Boolean pressed = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        FacebookSdk.fullyInitialize();
        //FacebookSdk.sdkInitialize(this);
        AppEventsLogger.activateApp(getApplication());

        findview();

        fauth = FirebaseAuth.getInstance();
        registeration_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CreateAccount.class));
            }
        });

        LoginViewFatory factory = new LoginViewFatory(this);
        viewmodel = ViewModelProviders.of(this, factory).get(LoginViewModel.class);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_id)).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(LogIn.this, gso);
        google_btn.setOnClickListener(v -> {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            launcher.launch(signInIntent);
        });
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getData() != null) {
                Task<GoogleSignInAccount> task =
                        GoogleSignIn.getSignedInAccountFromIntent(result.getData());
                handleSignInWithGoogle(task);
            }
        });
        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
            }
        });
        facebook_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(LogIn.this, Arrays.asList("email", "public_profile"));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getemail = memail.getText().toString().trim();
                String getpassword = mpassword.getText().toString().trim();
                if (TextUtils.isEmpty(getemail) && TextUtils.isEmpty(getpassword)) {
                    Toast.makeText(LogIn.this, "Fields are required", Toast.LENGTH_SHORT).show();
                }else{
                   if (TextUtils.isEmpty(getemail)||TextUtils.isEmpty(getpassword) || Integer.parseInt(getpassword) < 6) {
                        Toast.makeText(getApplicationContext(), "Please fill Fields ", Toast.LENGTH_SHORT).show();
                    }else{
                        viewmodel.LogIn(getemail, getpassword);

                    }
                }

            }
        });
        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ForgetPassword.class));
            }
        });
    }

    private void graphRequest(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String name = object.getString("name");
                    String email = object.getString("email");
                    User userclass = new User(name, email, "Customer");
                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(uid)
                            .setValue(userclass);
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        });

        Bundle bundle = new Bundle();
        bundle.putString(
                "fields",
                "name,email"
        );
        request.setParameters(bundle);
        request.executeAsync();
    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        fauth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LogIn.this, "Login Successed", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LogIn.this, ViewCustomer.class));
                    graphRequest(token);
                } else {
                    Toast.makeText(LogIn.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void handleSignInWithGoogle(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            AuthWithGoogle(account);
            Toast.makeText(this, "Successfully" + account.getEmail(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), ViewCustomer.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } catch (ApiException e) {
            Toast.makeText(this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void AuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnSuccessListener(authResult -> {
                    UserProfileChangeRequest.Builder builder = new UserProfileChangeRequest.Builder()
                            .setDisplayName(account.getDisplayName());
                    SetValueWithGoogle(builder);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Faild Process", Toast.LENGTH_SHORT).show();
                });
    }

    @Override
    public void SetValueWithGoogle(UserProfileChangeRequest.Builder request) {
        FirebaseAuth.getInstance().getCurrentUser()
                .updateProfile(request.build())
                .addOnSuccessListener(unused -> {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    User userClas = new User(user.getDisplayName(), user.getEmail(), "Customer");
                    FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).setValue(userClas);
                });
    }


    public void findview() {
        memail = findViewById(R.id.email_login);
        mpassword = findViewById(R.id.password_login);
        login = findViewById(R.id.login_button);
        signup = findViewById(R.id.register);
        forget_password = findViewById(R.id.forget);
        google_btn = findViewById(R.id.google_login);
        facebook_btn = findViewById(R.id.face);
        registeration_email = findViewById(R.id.register);

    }

    @Override
    public void onBackPressed() {
        if(pressed){
            super.onBackPressed();
        }
        else{
            return;
        }
    }

}