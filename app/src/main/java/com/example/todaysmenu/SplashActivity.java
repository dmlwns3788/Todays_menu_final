package com.example.todaysmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.todaysmenu.MainActivity;

public class SplashActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SplashActivity.this.startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                SplashActivity.this.finish();
            }
        }, 1000);
        /*try {
            Thread.sleep(2000);
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        startActivity(new Intent(this, LoginActivity.class));
        finish();*/
    }
}
