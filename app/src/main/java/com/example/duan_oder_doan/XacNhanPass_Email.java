package com.example.duan_oder_doan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class XacNhanPass_Email extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText edt_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_pass_email);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        edt_email = findViewById(R.id.edt_email);

        findViewById(R.id.btn_next).setOnClickListener(v ->{
            resetPassword();
        });
    }

    private void resetPassword() {
        String email = edt_email.getText().toString();

        if (email.isEmpty()) {
            edt_email.setError("Yêu cầu nhập Email!");
            edt_email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edt_email.setError("Vui lòng cung cấp email hợp lệ!");
            edt_email.requestFocus();
            return;
        }
        auth = FirebaseAuth.getInstance();
        String emailAddress = email;
        auth.sendPasswordResetEmail(emailAddress).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(XacNhanPass_Email.this, "Kiểm tra email của bạn để thiết lập lại mật khẩu của bạn!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(XacNhanPass_Email.this, DangNhap.class);
                    Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(XacNhanPass_Email.this).toBundle();
                    startActivity(intent, bundle);
                }else {
                    Toast.makeText(XacNhanPass_Email.this,"Thử lại! Đã xảy ra sự cố!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}