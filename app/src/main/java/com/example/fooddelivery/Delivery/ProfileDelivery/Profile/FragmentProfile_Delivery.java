package com.example.fooddelivery.Delivery.ProfileDelivery.Profile;

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
import com.example.fooddelivery.Customer.Buttom_Navi.Profile.LoginProflle;
import com.example.fooddelivery.Delivery.ProfileDelivery.EditProfile.EditProfile_Delivery;
import com.example.fooddelivery.Login.LogInRegistration.LogIn;
import com.example.fooddelivery.R;
import com.example.fooddelivery.pojo.LoginViewFatory;
import com.example.fooddelivery.pojo.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FragmentProfile_Delivery extends Fragment implements View.OnClickListener {

    TextView  user_name,phone, location_info, support_info, language_info, activnotification_info, changepassword_info;
    Button exit,editprofile;
    FirebaseAuth fauth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
DeliveryProfileViewModel deliveryProfileViewModel ;


    public FragmentProfile_Delivery() {

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
        View v = inflater.inflate(R.layout.fragment_profile__delivery, container, false);
        LoginViewFatory fatory = new LoginViewFatory(getContext());
        deliveryProfileViewModel = ViewModelProviders.of(this, fatory).get(DeliveryProfileViewModel.class);

        viewBind(v);

        if (user == null) {
            startActivity(new Intent(getContext(), LoginProflle.class));
        } else {
            editprofile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getContext(), EditProfile_Delivery.class));
                }
            });
            Signout();


        }

        deliveryProfileViewModel.mutableLiveData.observe(requireActivity(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
          updateUi(user);
            }
        });
        deliveryProfileViewModel.getInformation();

        onClick();
 //  getInfo();


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
    }  public void viewBind(View v) {
        exit = v.findViewById(R.id.signout_delive);
        editprofile = v.findViewById(R.id.gotoLogin_mainprofile_delive);
        user_name = v.findViewById(R.id.name_mainprofile_delive);
        phone = v.findViewById(R.id.phone_profile_delive);
        location_info = v.findViewById(R.id.mainprofile_location_delive);
        support_info = v.findViewById(R.id.mainprofile_support_delive);
        language_info = v.findViewById(R.id.mainprofile_language_delive);
        activnotification_info = v.findViewById(R.id.mainprofile_active_delive);
        changepassword_info = v.findViewById(R.id.mainprofile_changepassword_delive);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.mainprofile_location_delive:
                Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();



                break;
            case R.id.mainprofile_support_delive:
                Toast.makeText(getActivity(), " Call this number 01016292039", Toast.LENGTH_LONG).show();
                break;
            case R.id.mainprofile_language_delive:
                Toast.makeText(getActivity(), " We will add This feature  soon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mainprofile_changepassword_delive:
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

public void SwitchInfo(View v , User user){

}
public void updateUi(User user){
    user_name.setText(user.getName());
    phone.setText(user.getPhone());
}
}