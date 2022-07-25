package com.example.fooddelivery.Admin.FragmentItems.Dessert;

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

public class FragmnetDessert extends Fragment implements RepoDessert.DessertInterface {
    EditText name, descri, price, time;
    Button add;
    String getname, getdes, getprice, gettime;
    DessertViewModel mvvm;
    Uri filePath;
    ImageView imageView;

    public FragmnetDessert() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LoginViewFatory factory = new LoginViewFatory(getActivity());
        mvvm = ViewModelProviders.of(this, factory).get(DessertViewModel.class);

        View v = inflater.inflate(R.layout.fragment_fragmnet_dessert, container, false);
        name = v.findViewById(R.id.name_dessert);

        price = v.findViewById(R.id.price_dessert);
        time = v.findViewById(R.id.time_dessert);
        imageView = v.findViewById(R.id.imageView5);
        add = v.findViewById(R.id.button2_dessert);
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
                Food food = new Food(getname, getdes, getprice, gettime, "Dessert");
             mvvm.Uploaddata(food,filePath);
            }
        });

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
    public void Successed() {
    }

    @Override
    public void Faild(String error) {

    }
}