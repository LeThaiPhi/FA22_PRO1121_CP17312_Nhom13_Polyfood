package com.example.duan_oder_doan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan_oder_doan.adapter.Adapter_Detailed_Invoice_User;
import com.example.duan_oder_doan.model.HoaDonChiTiet;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThongKeUser extends AppCompatActivity {

    private HoaDonChiTiet hoaDonChiTiet;
    private List<HoaDonChiTiet> hoaDonChiTietList;
    private TextView tv_sumoder, tv_summoney;
    private int sum1, sum2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_user);

        tv_sumoder = findViewById(R.id.tv_sumoder);
        tv_summoney = findViewById(R.id.tv_summoney);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_statistical);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        findViewById(R.id.btn_clear).setOnClickListener(v ->{
            final Dialog dialog = new Dialog(this, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
            dialog.setContentView(R.layout.dialog_xoa_thong_ke);
            dialog.findViewById(R.id.btn_agree).setOnClickListener(v1 ->{
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference("Users");
                reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("Detailed_Invoice").removeValue();
                reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("Food_Detailed_Invoices").removeValue();
                sum1 =0;
                sum2 = 0;
                Toast.makeText(ThongKeUser.this,"Delete statistical successfully!", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            });
            dialog.findViewById(R.id.btn_cancel).setOnClickListener(v1 ->{
                dialog.dismiss();
            });
            dialog.show();
        });

        hoaDonChiTietList = new ArrayList<>();
        getList();
    }

    private void getList(){
        hoaDonChiTietList.clear();
        sum1 =0;
        sum2 = 0;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Users");

        reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("Detailed_Invoice").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            hoaDonChiTiet = dataSnapshot.getValue(HoaDonChiTiet.class);
                            hoaDonChiTietList.add(hoaDonChiTiet);
                            sum1 = hoaDonChiTietList.size();
                            sum2 = sum2+ Integer.parseInt(hoaDonChiTiet.getSum_Price());
                        }
                        tv_sumoder.setText(String.valueOf(sum1));
                        tv_summoney.setText("$ "+(String.valueOf(sum2)));
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(ThongKeUser.this, "Get list faild!", Toast.LENGTH_LONG).show();
                    }
                });
    }
}