package com.example.todaysmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SellerMenuActivity extends AppCompatActivity {
    String menuString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "menu.db", null, 1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_menu);

        Button addmenu = findViewById(R.id.addmenuButton);
        final EditText menuText = (EditText)findViewById(R.id.addMenuText);
        final int login_id = getIntent().getIntExtra("login_id", 0);
        System.out.println(login_id);

        addmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuString = menuText.getText().toString();
                if(menuString.matches("")){
                    Toast.makeText(SellerMenuActivity.this, "추가할 메뉴를 입력하세요.", Toast.LENGTH_SHORT).show();
                }
                else{
                    // 입력한 menuString DB에 추가
                    Intent intent = new Intent(SellerMenuActivity.this, SellerMainActivity.class);
                    dbHelper.join_menu(menuString, login_id);
                    menuText.setText(null);
                    Toast.makeText(SellerMenuActivity.this, "추가완료", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Button addmenuExit = findViewById(R.id.addmenuExitButton);
        //addmenuExit.setOnClickListener(new View.OnClickListener() {
         //   @Override
          //  public void onClick(View view) {
          //      Intent intent2 = new Intent(SellerMenuActivity.this, SellerMainActivity.class);
           //     int login_id = intent2.getExtras().getInt("login_id");
           //     startActivity(intent2);
           // }
        //});
    }


}
