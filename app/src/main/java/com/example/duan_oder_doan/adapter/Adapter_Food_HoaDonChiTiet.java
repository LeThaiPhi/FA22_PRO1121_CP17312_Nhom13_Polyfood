package com.example.duan_oder_doan.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_oder_doan.R;
import com.example.duan_oder_doan.model.HoaDon;
import com.example.duan_oder_doan.model.HoaDonChiTiet;
import com.example.duan_oder_doan.view_holder.View_Holder_Food_HoaDonChiTiet;
import com.example.duan_oder_doan.view_holder.View_Holder_Receipt_User;

import java.util.List;

public class Adapter_Food_HoaDonChiTiet extends RecyclerView.Adapter<View_Holder_Food_HoaDonChiTiet> {
    private List<HoaDon> hoaDonList;

    public Adapter_Food_HoaDonChiTiet(List<HoaDon> hoaDonList) {
        this.hoaDonList = hoaDonList;
    }

    @NonNull
    @Override
    public View_Holder_Food_HoaDonChiTiet onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row =inflater.inflate(R.layout.item_food_hoadonchitiet, parent, false);

        View_Holder_Food_HoaDonChiTiet viewHolderFoodHoaDonChiTiet = new View_Holder_Food_HoaDonChiTiet(row);
        return viewHolderFoodHoaDonChiTiet;
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder_Food_HoaDonChiTiet holder, int position) {
        HoaDon hoaDon = hoaDonList.get(position);

        holder.tvNameFood.setText(hoaDon.getName_Food());
        holder.tvNoteFood.setText(hoaDon.getNote_Food());
        holder.tvQuantityFood.setText(hoaDon.getQuantity_Food());
    }

    @Override
    public int getItemCount() {
        return hoaDonList == null ? 0 : hoaDonList.size();
    }
}
