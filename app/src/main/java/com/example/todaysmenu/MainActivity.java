package com.example.todaysmenu;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txt = (TextView)findViewById(R.id.text1);
        id=getIntent().getStringExtra(id);
        txt.setText(id);
    }
}
