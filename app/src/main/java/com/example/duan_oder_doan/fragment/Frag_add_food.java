package com.example.duan_oder_doan.fragment;

import android.app.Dialog;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_oder_doan.R;
import com.example.duan_oder_doan.adapter.Adapter_Category_Admin;
import com.example.duan_oder_doan.adapter.Adapter_Food_Admin;
import com.example.duan_oder_doan.model.SanPham;
import com.example.duan_oder_doan.model.TheLoai;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Frag_add_food  extends Fragment implements Adapter_Food_Admin.Callback {
    private EditText edt_idFood;
    private EditText edt_nameFood;
    private EditText edt_priceFood;
    private EditText edt_noteFood;
    private Spinner spn_category;
    private ImageView img_food;

    private List<SanPham> sanPhamList;
    private Adapter_Food_Admin adapter;
    private RecyclerView recyclerView;
    private SanPham sanPham;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_add_food, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rcv_food);

        view.findViewById(R.id.floatFood).setOnClickListener(v -> {
            final Dialog dialog = new Dialog(getContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
            dialog.setContentView(R.layout.dialog_add_food);
            edt_idFood = dialog.findViewById(R.id.edt_idFood);
            edt_nameFood = dialog.findViewById(R.id.edt_nameFood);
            edt_priceFood = dialog.findViewById(R.id.edt_priceFood);
            edt_noteFood = dialog.findViewById(R.id.edt_noteFood);
            spn_category = dialog.findViewById(R.id.spn_category);
            img_food = dialog.findViewById(R.id.img_food);


            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference("Categories");
            List<TheLoai> theLoaiList = new ArrayList<>();
            ArrayAdapter<TheLoai> theLoaiArrayAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_text, theLoaiList);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        TheLoai theLoai = dataSnapshot.getValue(TheLoai.class);
                        theLoaiList.add(theLoai);
                    }
                    theLoaiArrayAdapter.notifyDataSetChanged();
                    spn_category.setAdapter(theLoaiArrayAdapter);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getContext(), "Get list faild!", Toast.LENGTH_LONG).show();
                }
            });


            dialog.findViewById(R.id.btn_save).setOnClickListener(view1 -> {
                String nameFood = edt_nameFood.getText().toString();
                String idFood = edt_idFood.getText().toString();
                String priceFood = edt_priceFood.getText().toString();
                String note = edt_noteFood.getText().toString();
                String category = spn_category.getSelectedItem().toString();
                int image = R.drawable.avatar;


                if (idFood.isEmpty()) {
                    edt_idFood.setError("ID Category is required");
                    edt_idFood.requestFocus();
                    return;
                }

                if (nameFood.isEmpty()) {
                    edt_nameFood.setError("Name Category is required");
                    edt_nameFood.requestFocus();
                    return;
                }

                if (priceFood.isEmpty()) {
                    edt_priceFood.setError("Name Category is required");
                    edt_priceFood.requestFocus();
                    return;
                }

                sanPham = new SanPham(idFood,category, image, nameFood, priceFood, note);
                FirebaseDatabase.getInstance().getReference("Foods")
                        .child(idFood)
                        .setValue(sanPham).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getContext(), "Add category successfully!", Toast.LENGTH_LONG).show();
                                    dialog.dismiss();
                                }
                            }
                        });
            });
            dialog.show();
        });

        sanPhamList = new ArrayList<>();
        adapter = new Adapter_Food_Admin(sanPhamList, this);
        getList();

        EditText edt_searchFood = view.findViewById(R.id.edt_searchFood);
        edt_searchFood.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }

    private void filter(String text) {
        ArrayList<SanPham> filteredList = new ArrayList<>();
        for (SanPham sanPham: sanPhamList){
            if (sanPham.getName_product().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(sanPham);
            }
        }
        adapter.filterList(filteredList);
    }

    private void getList(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Foods");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    sanPham = dataSnapshot.getValue(SanPham.class);
                    sanPhamList.add(sanPham);
                }
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Get list faild!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void update(SanPham sanPham) {
        final Dialog dialog = new Dialog(getContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_update_food);
        EditText edt_nameFoodUpdate = dialog.findViewById(R.id.edt_nameFoodUpdate);
        EditText edt_priceFoodUpdate = dialog.findViewById(R.id.edt_priceFoodUpdate);
        EditText edt_noteFoodUpdate = dialog.findViewById(R.id.edt_noteFoodUpdate);
        Spinner spn_categoryUpdate = dialog.findViewById(R.id.spn_categoryUpdate);
        ImageView img_foodUpdate = dialog.findViewById(R.id.img_foodUpdate);

        edt_nameFoodUpdate.setText(sanPham.getName_product());
        edt_priceFoodUpdate.setText(sanPham.getPrice_product());
        edt_noteFoodUpdate.setText(sanPham.getNote_product());
        img_foodUpdate.setImageResource(sanPham.getImg_product());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Categories");
        List<TheLoai> theLoaiList = new ArrayList<>();
        ArrayAdapter<TheLoai> theLoaiArrayAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_text, theLoaiList);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    TheLoai theLoai = dataSnapshot.getValue(TheLoai.class);
                    theLoaiList.add(theLoai);
                }
                theLoaiArrayAdapter.notifyDataSetChanged();
                spn_categoryUpdate.setAdapter(theLoaiArrayAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Get list faild!", Toast.LENGTH_LONG).show();
            }
        });
        dialog.findViewById(R.id.btn_save).setOnClickListener(view1 -> {
            String nameFood = edt_nameFoodUpdate.getText().toString();
            String priceFood = edt_priceFoodUpdate.getText().toString();
            String note = edt_noteFoodUpdate.getText().toString();
            String category = spn_categoryUpdate.getSelectedItem().toString();
            int image = R.drawable.avatar;

            if (nameFood.isEmpty()) {
                edt_nameFood.setError("Name Category is required");
                edt_nameFood.requestFocus();
                return;
            }

            if (priceFood.isEmpty()) {
                edt_priceFood.setError("Name Category is required");
                edt_priceFood.requestFocus();
                return;
            }

            SanPham sanPham1 = new SanPham(sanPham.getId(), category, image, nameFood, priceFood, note);
            FirebaseDatabase.getInstance().getReference("Foods")
                    .child(sanPham1.getId())
                    .setValue(sanPham1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getContext(), "Add category successfully!", Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                        }
                    });
        });
        dialog.show();
    }
}
