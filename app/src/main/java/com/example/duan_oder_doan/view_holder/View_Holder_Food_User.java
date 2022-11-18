package com.example.duan_oder_doan.view_holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_oder_doan.R;

public class View_Holder_Food_User extends RecyclerView.ViewHolder {
    public LinearLayout lineItemFood;
    public TextView tvNameFood;
    public TextView tvNoteFood;

    public View_Holder_Food_User(@NonNull View itemView) {
        super(itemView);
        lineItemFood = (LinearLayout) itemView.findViewById(R.id.line_itemFood);
        tvNameFood = (TextView) itemView.findViewById(R.id.tv_nameFood);
        tvNoteFood = (TextView) itemView.findViewById(R.id.tv_noteFood);
    }
}
