package com.example.todaysmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SellerMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_main);
        setTitle("판매자 메뉴");
        final int login_id = getIntent().getIntExtra("login_id", 0);

        Button insertMenu = findViewById(R.id.insertMenuButton);
        insertMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerMainActivity.this, SellerMenuActivity.class);
                intent.putExtra("login_id",login_id);
                startActivity(intent);
            }
        });

        Button deleteMenu = findViewById(R.id.deleteMenuButton);
        deleteMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerMainActivity.this, SellerMenuDeleteActivity.class);
                intent.putExtra("login_id",login_id);
                startActivity(intent);
            }
        });

        Button logout = findViewById(R.id.logoutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerMainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
