package com.example.todaysmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class JoinActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
    }

    public void onClick(View view) {
        CheckBox termsCheck = (CheckBox)findViewById(R.id.termsCheck);
        if(termsCheck.isChecked()) {
            Intent intent = new Intent(JoinActivity.this, JoinInfoActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "약관에 동의하시기 바랍니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
