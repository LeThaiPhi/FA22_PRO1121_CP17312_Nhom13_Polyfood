package com.example.duan_oder_doan.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.duan_oder_doan.fragment.Frag_add_category;
import com.example.duan_oder_doan.fragment.Frag_add_food;

public class PagerAdapter_AddFood extends FragmentStateAdapter {
    public PagerAdapter_AddFood(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Frag_add_category();
            case 1:
                return new Frag_add_food();
            default:
                return new Frag_add_category();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
