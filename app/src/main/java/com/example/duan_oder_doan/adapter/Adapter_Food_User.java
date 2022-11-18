package com.example.duan_oder_doan.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_oder_doan.R;
import com.example.duan_oder_doan.model.SanPham;
import com.example.duan_oder_doan.view_holder.View_Holder_Food_Admin;
import com.example.duan_oder_doan.view_holder.View_Holder_Food_User;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

public class Adapter_Food_User extends RecyclerView.Adapter<View_Holder_Food_User> {
    private List<SanPham> sanPhamList;
    private Callback callback;

    public Adapter_Food_User(List<SanPham> sanPhamList, Callback callback) {
        this.sanPhamList = sanPhamList;
        this.callback = callback;
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
        holder.lineItemFood.setOnLongClickListener(v ->{
            callback.open(sanPham);
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamList == null ? 0 : sanPhamList.size();
    }
    public  interface Callback{
        void open(SanPham sanPham);
    }

}
