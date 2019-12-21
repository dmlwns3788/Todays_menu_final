package com.example.todaysmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SellerInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_info);
        setTitle("판매정보 등록");

        Button intentButton1 = findViewById(R.id.button1);
        intentButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerInfoActivity.this, SellerMainActivity.class);
                Toast.makeText(SellerInfoActivity.this, "수정완료", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        Button intentButton2 = findViewById(R.id.button2);
        intentButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerInfoActivity.this, SellerMainActivity.class);
                startActivity(intent);
            }
        });

    }
}
