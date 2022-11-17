package com.example.duan_oder_doan.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan_oder_doan.R;
import com.example.duan_oder_doan.model.TheLoai;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Frag_add_category extends Fragment {
    FloatingActionButton floatCategory;
    private EditText ed_nameCategory;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_add_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        floatCategory = view.findViewById(R.id.floatCategory);
        ed_nameCategory = view.findViewById(R.id.edName);

        floatCategory.setOnClickListener(view1 -> {
            Dialog dialog = new Dialog(getContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
            dialog.setContentView(R.layout.dialog_add_category);

            dialog.findViewById(R.id.btnSave).setOnClickListener(view2 -> {
                int img_category = R.drawable.anh_banh_mi;
                String nameCategory = ed_nameCategory.getText().toString();
                TheLoai theLoai = new TheLoai(img_category, nameCategory);
                FirebaseDatabase firebaseDatabase =  FirebaseDatabase.getInstance();
                DatabaseReference reference =  firebaseDatabase.getReference("Categoris");
                String uID =  FirebaseDatabase.getInstance().getReference().push().getKey();
                reference.child(uID).setValue(theLoai)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getContext(), "Add category success", Toast.LENGTH_SHORT).show();
                            }
                        });

            });
            dialog.show();
        });
        }
    }
