package com.example.fooddelivery.Admin.AdminPannel.Fragmentpannel.UsersPannel.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.fooddelivery.Customer.Buttom_Navi.Profile.ChangePasswordProfile;
import com.example.fooddelivery.Customer.Buttom_Navi.Profile.EditProfile;
import com.example.fooddelivery.Customer.Buttom_Navi.Profile.LoginProflle;
import com.example.fooddelivery.Login.LogInRegistration.LogIn;
import com.example.fooddelivery.R;
import com.example.fooddelivery.pojo.LoginViewFatory;
import com.example.fooddelivery.pojo.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FragmentProfile_Admin extends Fragment implements View.OnClickListener {

    TextView user_name, phone, location_info, support_info, language_info, activnotification_info, changepassword_info;
    Button exit, editprofile;
    FirebaseAuth fauth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    AdminProfileViewModel viewModel;

    public FragmentProfile_Admin() {

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
        View v = inflater.inflate(R.layout.fragment_profile__admin, container, false);
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
        LoginViewFatory factory = new LoginViewFatory(getContext());
        viewModel = ViewModelProviders.of(this, factory).get(AdminProfileViewModel.class);
        viewModel.mutableLiveData.observe(requireActivity(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
updateUi(user);
            }
        });
        viewModel.getInformation();
        onClick();

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
        exit = v.findViewById(R.id.signout_ad);
        editprofile = v.findViewById(R.id.gotoLogin_mainprofile_ad);
        user_name = v.findViewById(R.id.name_mainprofile_ad);
        phone = v.findViewById(R.id.phone_profile_ad);
        location_info = v.findViewById(R.id.mainprofile_location_ad);
        support_info = v.findViewById(R.id.mainprofile_support_ad);
        language_info = v.findViewById(R.id.mainprofile_language_ad);
        activnotification_info = v.findViewById(R.id.mainprofile_active_ad);
        changepassword_info = v.findViewById(R.id.mainprofile_changepassword_ad);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mainprofile_location_ad:

        Toast.makeText(getContext(), "Location is : "+ viewModel.mutableLiveData.getValue().getLocation(), Toast.LENGTH_LONG).show();

             break;
            case R.id.mainprofile_support_ad:
                Toast.makeText(getActivity(), " Call this number 01016292039", Toast.LENGTH_LONG).show();
                break;
            case R.id.mainprofile_language_ad:
                Toast.makeText(getActivity(), " We will add This feature  soon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mainprofile_changepassword_ad:
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

    public void updateUi(User user) {
        user_name.setText(user.getName());
        phone.setText(user.getPhone());
    }

}