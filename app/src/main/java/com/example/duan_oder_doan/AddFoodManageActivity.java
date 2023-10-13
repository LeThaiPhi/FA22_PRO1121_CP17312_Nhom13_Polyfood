package com.example.duan_oder_doan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.duan_oder_doan.adapter.PagerAdapter_AddFood;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class AddFoodManageActivity extends AppCompatActivity {
    ViewPager2 viewPagerAddFood;
    TabLayout tabLayoutAddFood;
    PagerAdapter_AddFood adapter_addFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        viewPagerAddFood = findViewById(R.id.viewpager2_add_food);
        tabLayoutAddFood = findViewById(R.id.tablayout_add_food);

        adapter_addFood = new PagerAdapter_AddFood(this);
        viewPagerAddFood.setAdapter(adapter_addFood);

        new TabLayoutMediator(tabLayoutAddFood, viewPagerAddFood, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Thêm loại món ăn");
                        break;
                    case 1:
                        tab.setText("Thêm món ");
                        break;
                    case 2:
                        tab.setText("Món ăn phổ biến");
                        break;
                }
            }
        }).attach();
    }
}