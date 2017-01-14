package com.webianks.educorp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                showLoginRegister();
            }
        }, 1000);

    }

    private void showLoginRegister() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
