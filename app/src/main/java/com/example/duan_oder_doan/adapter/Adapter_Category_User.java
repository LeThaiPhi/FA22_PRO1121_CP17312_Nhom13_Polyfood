package com.example.duan_oder_doan.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_oder_doan.R;
import com.example.duan_oder_doan.model.TheLoai;
import com.example.duan_oder_doan.view_holder.View_Holder_Category_Admin;
import com.example.duan_oder_doan.view_holder.View_Holder_Category_User;

import java.util.List;

import javax.security.auth.callback.Callback;

public class Adapter_Category_User extends RecyclerView.Adapter<View_Holder_Category_User> {
    private List<TheLoai> theLoaiList;
    private Callback callback;

    public Adapter_Category_User(List<TheLoai> theLoaiList, Callback callback) {
        this.theLoaiList = theLoaiList;
        this.callback = callback;
    }

    @NonNull
    @Override
    public View_Holder_Category_User onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row =inflater.inflate(R.layout.item_category_user, parent, false);

        View_Holder_Category_User viewHolderCategoryUser = new View_Holder_Category_User(row);
        return viewHolderCategoryUser;
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder_Category_User holder, int position) {
        TheLoai theLoai = theLoaiList.get(position);

        holder.tvNameCategory.setText(theLoai.getName_category());
        holder.imgCategory.setImageResource(theLoai.getImg_category());
        holder.lineItemCategory.setOnClickListener(v ->{
            callback.open(theLoai);
        });
    }

    @Override
    public int getItemCount() {
        return theLoaiList == null ? 0 : theLoaiList.size();
    }

    public  interface Callback{
        void open(TheLoai theLoai);
    }
}
