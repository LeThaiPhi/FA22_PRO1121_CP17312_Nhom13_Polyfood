package com.example.duan_oder_doan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class TrangChuManage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu_manage);

        findViewById(R.id.layoutOders).setOnClickListener(view -> {
            Intent intent = new Intent(TrangChuManage.this, OdersManageActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.layoutStatistical).setOnClickListener(view -> {
            Intent intent = new Intent(TrangChuManage.this, StatisticalManageActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.layoutAdd).setOnClickListener(view -> {
            Intent intent = new Intent(TrangChuManage.this, AddFoodManageActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.layoutAdmin).setOnClickListener(view -> {
            Intent intent = new Intent(TrangChuManage.this, AdminManageActivity.class);
            startActivity(intent);
        });
    }
}