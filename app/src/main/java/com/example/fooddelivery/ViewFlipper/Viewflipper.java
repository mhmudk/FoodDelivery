package com.example.fooddelivery.ViewFlipper;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.fooddelivery.Customer.ViewCustomer;
import com.example.fooddelivery.R;

public class Viewflipper extends AppCompatActivity {
    Button next;
    ViewPager pagger;
    TextView tskip;
    int layout[] = {R.layout.viewvlifpper_one, R.layout.viewvlifpper_two, R.layout.viewvlifpper_three};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewflipper);
        findview();
        tskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ViewCustomer.class));
            }
        });
        pagger.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (position == 2) {
                    next.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        pagger.setAdapter(new PagerAdapter() {
            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View v = layoutInflater.inflate(layout[position], container, false);
                container.addView(v);
                return v;
            }

            @Override
            public int getCount() {
                return layout.length;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                View v = (View) object;
                container.removeView(v);

            }
        });
        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int current = pagger.getCurrentItem();
                if (current < layout.length - 1) {
                    pagger.setCurrentItem(current + 1);
                } else {
                    launchDashboard();
                }
            }
        });
    }

    private void launchDashboard() {
        startActivity(new Intent(this, KeepOn.class));
    }

    private void findview() {
        pagger = findViewById(R.id.pager);
        next = findViewById(R.id.next);
        tskip = findViewById(R.id.skip);

    }

}