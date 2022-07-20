package com.example.fooddelivery.Admin.FragmentItems.Burger;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.fooddelivery.pojo.LoginViewFatory;
import com.example.fooddelivery.R;
import com.example.fooddelivery.pojo.Food;
import com.google.firebase.auth.FirebaseAuth;


public class FragmentBurger extends Fragment implements RepoBurger.BurgerInterface {
    EditText name, descri, price, time;
    Button add, signout;
    String getname, getdes, getprice, gettime;
    BurgerViewModel mvvm;
    public ImageView imageView;
    public Uri filePath;
    FirebaseAuth auth;

    public FragmentBurger() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LoginViewFatory factory = new LoginViewFatory(getActivity());
        mvvm = ViewModelProviders.of(this, factory).get(BurgerViewModel.class);
        View v = inflater.inflate(R.layout.fragment_burger, container, false);
        name = v.findViewById(R.id.name_burger);
        price = v.findViewById(R.id.price_burger);
        descri = v.findViewById(R.id.description_burger_);
        time = v.findViewById(R.id.time_burger);
        imageView = v.findViewById(R.id.imageView6);
        add = v.findViewById(R.id.button2_burger);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grapPhoto();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getname = name.getText().toString().trim();
                getdes = descri.getText().toString().trim();
                getprice = price.getText().toString().trim();
                gettime = time.getText().toString().trim();
                Food food = new Food(getname, getdes, getprice,
                        gettime, "Burger");
                mvvm.pushh(food, filePath);
                //mvvm.uploadData(food,filePath);
            }
        });
        /*
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                getContext().startActivity(new Intent(getContext(), LogIn.class));

            }
        });
        */
        return v;
    }


    private void grapPhoto() {
        Intent galleryIntent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, 100);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (data != null) {
                filePath = data.getData();
                imageView.setImageURI(filePath);
            }
        }
    }


    @Override
    public void Successed(Food food) {
        name.setText("");
        descri.setText("");
        price.setText("");
        time.setText("");

    }

    @Override
    public void Faild(String error) {
    }
}