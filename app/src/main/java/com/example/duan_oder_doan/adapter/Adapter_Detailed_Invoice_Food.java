package com.example.duan_oder_doan.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_oder_doan.R;
import com.example.duan_oder_doan.model.HoaDon;
import com.example.duan_oder_doan.view_holder.View_Holder_Detailed_Invoice_Food;
import com.example.duan_oder_doan.view_holder.View_Holder_Receipt_User;

import java.util.List;

public class Adapter_Detailed_Invoice_Food extends RecyclerView.Adapter<View_Holder_Detailed_Invoice_Food> {
    private List<HoaDon> hoaDonList;

    public Adapter_Detailed_Invoice_Food(List<HoaDon> hoaDonList) {
        this.hoaDonList = hoaDonList;
    }

    @NonNull
    @Override
    public View_Holder_Detailed_Invoice_Food onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row =inflater.inflate(R.layout.item_food_hoadonchitiet, parent, false);

        View_Holder_Detailed_Invoice_Food viewHolderDetailedInvoiceFood = new View_Holder_Detailed_Invoice_Food(row);
        return viewHolderDetailedInvoiceFood;
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder_Detailed_Invoice_Food holder, int position) {
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
