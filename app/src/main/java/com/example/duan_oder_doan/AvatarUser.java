package com.example.duan_oder_doan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.duan_oder_doan.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class AvatarUser extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    private CircleImageView img_avatar;
    private String name, email, pass, phone, gender, date_of_birth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_user);

        img_avatar = findViewById(R.id.img_avatar);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {
                    int image1 = userProfile.getImage();
                    img_avatar.setImageResource(image1);
                    email = userProfile.getEmail();
                    pass = userProfile.getPassword();
                    name = userProfile.getFullName();
                    phone = userProfile.getPhone();
                    gender = userProfile.getGender();
                    date_of_birth = userProfile.getDate_of_birth();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AvatarUser.this,"Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.btn_update).setOnClickListener(v ->{
            int image = R.drawable.avatar;
            User userProfile1 = new User(name, email, phone,pass, image, gender, date_of_birth);
            userProfile1.setImage(img_avatar.getImageAlpha());
            FirebaseDatabase.getInstance().getReference("Users")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .setValue(userProfile1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(AvatarUser.this,"User avatar updated!", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(AvatarUser.this, GioiThieuUser.class);
                                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(AvatarUser.this).toBundle();
                                startActivity(intent, bundle);
                            }
                        }
                    });
        });

        findViewById(R.id.ln_avatar).setOnClickListener(v ->{
            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_bottom_anh);
            dialog.findViewById(R.id.tv_choose_photo).setOnClickListener(view ->{
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 999);
                } else {
                    try {
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(takePictureIntent, 1111);
                    } catch (ActivityNotFoundException e){
                    }
                }
                dialog.dismiss();
            });
            dialog.findViewById(R.id.tv_to_take_photo).setOnClickListener(view ->{
                try {
                    Intent i = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, 999);
                } catch (Exception exp) {
                    Log.i("Error", exp.toString());
                }
                dialog.dismiss();
            });
            dialog.show();
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            dialog.getWindow().setGravity(Gravity.BOTTOM);
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_avatar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //chụp ảnh
        if (requestCode == 1111) {
            Bitmap b = (Bitmap) data.getExtras().get("data");
            img_avatar.setImageBitmap(b);
        }

        //lấy ảnh
        if (requestCode == 999) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                img_avatar.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }


        }
    }
}