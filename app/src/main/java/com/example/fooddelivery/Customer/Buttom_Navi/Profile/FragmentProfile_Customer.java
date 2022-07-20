package com.example.fooddelivery.Customer.Buttom_Navi.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fooddelivery.Login.LogInRegistration.LogIn;
import com.example.fooddelivery.R;
import com.example.fooddelivery.pojo.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FragmentProfile_Customer extends Fragment implements View.OnClickListener {

    TextView  user_name,phone, location_info, support_info, language_info, activnotification_info, changepassword_info;
    Button exit,editprofile;
    FirebaseAuth fauth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    public FragmentProfile_Customer() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (user == null)
            startActivity(new Intent(getContext(), LoginProflle.class));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile__customer, container, false);
        viewBind(v);

        if (user == null) {
            startActivity(new Intent(getContext(), LoginProflle.class));
        } else {
            editprofile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getContext(), EditProfile.class));
                }
            });
            Signout();


        }

        onClick();
        getInfo();
        return v;

    }


    public void Signout() {
        fauth = FirebaseAuth.getInstance();
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.web_id))
                        .requestEmail()
                        .build();
                GoogleSignInClient client = GoogleSignIn.getClient(requireContext(), gso);
                if (GoogleSignIn.getLastSignedInAccount(requireContext()) != null)
                    client.signOut();

                else {
                    fauth.signOut();
                    startActivity(new Intent(getContext(), LogIn.class));
                }
            }
        });
    }

    public void viewBind(View v) {
        exit = v.findViewById(R.id.signout);
        editprofile = v.findViewById(R.id.gotoLogin_mainprofile);
        user_name = v.findViewById(R.id.name_mainprofile);
        phone = v.findViewById(R.id.phone_profile);
        location_info = v.findViewById(R.id.mainprofile_location);
        support_info = v.findViewById(R.id.mainprofile_support);
        language_info = v.findViewById(R.id.mainprofile_language);
        activnotification_info = v.findViewById(R.id.mainprofile_active);
        changepassword_info = v.findViewById(R.id.mainprofile_changepassword);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mainprofile_location:
//عادى احط الكود كدا ولا اعمله Functuin ?
                FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                User user = snapshot.getValue(User.class);
                                String location = user.getLocation();
                                Toast.makeText(getContext(), "Location is : " + location, Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                break;
            case R.id.mainprofile_support:
                Toast.makeText(getActivity(), " Call this number 01016292039", Toast.LENGTH_LONG).show();
                break;
            case R.id.mainprofile_language:
                Toast.makeText(getActivity(), " We will add This feature  soon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mainprofile_changepassword:
                Intent intent = new Intent(getContext(), ChangePasswordProfile.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;

        }
    }

    public void onClick() {
        location_info.setOnClickListener(this);
        language_info.setOnClickListener(this);
        changepassword_info.setOnClickListener(this);
        support_info.setOnClickListener(this);
    }

public void getInfo()
{
    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
            .addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User user = snapshot.getValue(User.class);
                 user_name.setText(user.getName());
                 phone.setText(user.getPhone());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
}

}