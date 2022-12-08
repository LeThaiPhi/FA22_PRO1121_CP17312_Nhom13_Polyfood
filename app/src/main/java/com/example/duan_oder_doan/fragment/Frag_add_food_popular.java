package com.example.duan_oder_doan.fragment;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_oder_doan.R;
import com.example.duan_oder_doan.TrangChuUser;
import com.example.duan_oder_doan.YeuThichUser;
import com.example.duan_oder_doan.adapter.Adapter_Favorite;
import com.example.duan_oder_doan.adapter.Adapter_Food_Admin;
import com.example.duan_oder_doan.adapter.Adapter_Food_Popular_Admin;
import com.example.duan_oder_doan.model.SanPham;
import com.example.duan_oder_doan.model.SanPham_Favorite;
import com.example.duan_oder_doan.model.SanPham_Popular;
import com.example.duan_oder_doan.model.TheLoai;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frag_add_food_popular extends Fragment {

    private List<SanPham_Popular> sanPham_popularList;
    private Adapter_Food_Popular_Admin adapter;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_add_food_popular, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rcv_popular);
        sanPham_popularList = new ArrayList<>();
        adapter = new Adapter_Food_Popular_Admin(sanPham_popularList);
        getList();
    }

    private void getList(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference1 = database.getReference("FoodPopulars");

        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    SanPham_Popular sanPham_popular = dataSnapshot.getValue(SanPham_Popular.class);
                    sanPham_popularList.add(sanPham_popular);
                }
                Collections.reverse(sanPham_popularList);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Get list category faild!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
