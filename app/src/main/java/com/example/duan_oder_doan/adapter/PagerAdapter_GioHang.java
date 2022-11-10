package com.example.duan_oder_doan.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.duan_oder_doan.fragment.Frag_History;
import com.example.duan_oder_doan.fragment.Frag_Oder;

public class PagerAdapter_GioHang extends FragmentStateAdapter {
    int soluong=2;

    public PagerAdapter_GioHang(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new Frag_Oder();
            case 1: return new Frag_History();
            default: return new Frag_History();
        }
    }

    @Override
    public int getItemCount() {
        return soluong;
    }
}
