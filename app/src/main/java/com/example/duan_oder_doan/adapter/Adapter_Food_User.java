package com.example.duan_oder_doan.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_oder_doan.HoaDonUser;
import com.example.duan_oder_doan.R;
import com.example.duan_oder_doan.model.SanPham;
import com.example.duan_oder_doan.view_holder.View_Holder_Food_User;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Food_User extends RecyclerView.Adapter<View_Holder_Food_User> {
    private List<SanPham> sanPhamList;

    public Adapter_Food_User(List<SanPham> sanPhamList) {
        this.sanPhamList = sanPhamList;
    }

    @NonNull
    @Override
    public View_Holder_Food_User onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row =inflater.inflate(R.layout.item_food_user, parent, false);

        View_Holder_Food_User viewHolderFoodUser = new View_Holder_Food_User(row);
        return viewHolderFoodUser;
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder_Food_User holder, int position) {
        SanPham sanPham = sanPhamList.get(position);

        holder.tvNameFood.setText(sanPham.getName_product());
        holder.tvNoteFood.setText(sanPham.getNote_product());
        holder.lineItemFood.setOnClickListener(v ->{
            Intent myIntent = new Intent(v.getContext(), HoaDonUser.class);
            Bundle bundle = new Bundle();
            bundle.putString("nameFood", sanPham.getName_product());
            bundle.putString("noteFood", sanPham.getNote_product());
            bundle.putString("priceFood", sanPham.getPrice_product());
            myIntent.putExtra("food", bundle);
            v.getContext().startActivity(myIntent);
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamList == null ? 0 : sanPhamList.size();
    }
    public  interface Callback{
        void open(SanPham sanPham);
    }

    public void filterList(ArrayList<SanPham> filteredList) {
        sanPhamList = filteredList;
        notifyDataSetChanged();
    }
}
