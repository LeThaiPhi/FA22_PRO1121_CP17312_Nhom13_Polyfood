package com.example.duan_oder_doan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.duan_oder_doan.adapter.Adapter_Detailed_Invoice_Admin;
import com.example.duan_oder_doan.adapter.Adapter_Detailed_Invoice_Chef;
import com.example.duan_oder_doan.model.HoaDonChiTietAdmin;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TrangChuChef extends AppCompatActivity {

    private HoaDonChiTietAdmin hoaDonChiTietAdmin;
    private List<HoaDonChiTietAdmin> hoaDonChiTietAdminList;
    private Adapter_Detailed_Invoice_Chef adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu_chef);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        recyclerView = findViewById(R.id.rcv_oder);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        hoaDonChiTietAdminList = new ArrayList<>();
        adapter = new Adapter_Detailed_Invoice_Chef(hoaDonChiTietAdminList);
        getList();
    }

    private void getList(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Detailed_Invoices");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    hoaDonChiTietAdmin = dataSnapshot.getValue(HoaDonChiTietAdmin.class);
                    hoaDonChiTietAdminList.add(hoaDonChiTietAdmin);
                }
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TrangChuChef.this, "Get list faild!", Toast.LENGTH_LONG).show();
            }
        });
    }
}