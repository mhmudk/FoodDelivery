package com.example.fooddelivery.Admin.FragmentItems.Drink;

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

public class FragmentDrink extends Fragment {
    EditText name, price;
    Button add;
    String getname, getprice;
    DrinkViewModel mvvm;
    ImageView imageView;
    Uri filPath;

    public FragmentDrink() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LoginViewFatory factory = new LoginViewFatory(getActivity());
        mvvm = ViewModelProviders.of(this, factory).get(DrinkViewModel.class);
        View v = inflater.inflate(R.layout.fragment_drink, container, false);
        name = v.findViewById(R.id.name_drink);
        price = v.findViewById(R.id.price_drink);
        add = v.findViewById(R.id.button2_dessert);
        imageView = v.findViewById(R.id.imageView5_drink);
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
                getprice = price.getText().toString().trim();
                Food food = new Food(getname, getprice, "Drink");
                mvvm.Uploaddata(food, filPath);
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
                filPath = data.getData();
                imageView.setImageURI(filPath);
            }
        }
    }
}