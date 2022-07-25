package com.example.fooddelivery.Admin.FragmentItems.Fries;

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

import com.example.fooddelivery.Admin.FragmentItems.Burger.BurgerViewModel;
import com.example.fooddelivery.pojo.LoginViewFatory;
import com.example.fooddelivery.R;
import com.example.fooddelivery.pojo.Food;


public class FragmentFries extends Fragment {
    EditText name, descri, price, time;
    Button add, signout;
    String getname, getdes, getprice, gettime;
    BurgerViewModel viewmodel;
    ImageView imageView;
    Uri filePath;


    public FragmentFries() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LoginViewFatory factory = new LoginViewFatory(getActivity());
        viewmodel = ViewModelProviders.of(this, factory).get(BurgerViewModel.class);
        View v = inflater.inflate(R.layout.fragment_fries, container, false);
        name = v.findViewById(R.id.name_fries);
        price = v.findViewById(R.id.price_fries);
        descri = v.findViewById(R.id.description_fries_);
        time = v.findViewById(R.id.time_fries);
        imageView = v.findViewById(R.id.fries);
        add = v.findViewById(R.id.button2_fries);
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
                        gettime, "Fries");
                viewmodel.pushh(food, filePath);

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


}