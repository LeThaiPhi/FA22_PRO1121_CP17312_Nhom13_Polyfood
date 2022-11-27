package com.example.duan_oder_doan.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_oder_doan.R;
import com.example.duan_oder_doan.model.SanPham;
import com.example.duan_oder_doan.model.TheLoai;
import com.example.duan_oder_doan.view_holder.View_Holder_Category_Admin;
import com.example.duan_oder_doan.view_holder.View_Holder_Food_Admin;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

public class Adapter_Food_Admin extends RecyclerView.Adapter<View_Holder_Food_Admin> {
    private List<SanPham> sanPhamList;
    private Callback callback;

    public Adapter_Food_Admin(List<SanPham> sanPhamList, Callback callback) {
        this.sanPhamList = sanPhamList;
        this.callback = callback;
    }

    @NonNull
    @Override
    public View_Holder_Food_Admin onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row =inflater.inflate(R.layout.item_food_admin, parent, false);

        View_Holder_Food_Admin viewHolderFoodAdmin = new View_Holder_Food_Admin(row);
        return viewHolderFoodAdmin;
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder_Food_Admin holder, int position) {
        SanPham sanPham = sanPhamList.get(position);

        holder.tvNameFood.setText(sanPham.getId()+". "+sanPham.getName_product());
        holder.tvPriceFood.setText(sanPham.getPrice_product());
        holder.tvCategoryFood.setText(sanPham.getCategory());
        holder.tvNoteFood.setText(sanPham.getNote_product());
        Picasso.get().load(sanPham.getImg_product()).into(holder.imgItemFood);
        holder.lineItemFood.setOnLongClickListener(v ->{
            callback.update(sanPham);
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamList == null ? 0 : sanPhamList.size();
    }

    public  interface Callback{
        void update(SanPham sanPham);
    }

    public void filterList(ArrayList<SanPham> filteredList) {
        sanPhamList = filteredList;
        notifyDataSetChanged();
    }
}
