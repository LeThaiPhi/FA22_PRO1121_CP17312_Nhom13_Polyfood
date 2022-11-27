package com.example.duan_oder_doan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class ThongBao extends AppCompatActivity {
    private Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_bao);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_advertise);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ImageView img_advertise = findViewById(R.id.img_advertise);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        int img = bundle.getInt("image");
        if (img == R.drawable.mhc1) {
            uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/duan-oder-doan.appspot.com/o/Images_Category%2F2515189.png?alt=media&token=af45cffb-1c9c-4eed-b78e-7720a41b0cf8");
        }
        if (img == R.drawable.mhc2) {
            uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/duan-oder-doan.appspot.com/o/7d25773445873512a96f59d1a2a65a03.jpg?alt=media&token=772ba3e3-b0a6-465e-8c2a-2eb8bc920560");
        }
        if (img == R.drawable.mhc4) {
            uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/duan-oder-doan.appspot.com/o/vdfood.png?alt=media&token=425bc41a-426c-477b-99f8-b2efa36ebc40");
        }
        img_advertise.setBackgroundResource(img);

        findViewById(R.id.btn_share).setOnClickListener(v ->{
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
            shareIntent.setType("image/jpeg");
            startActivity(Intent.createChooser(shareIntent, null));
        });


    }
}