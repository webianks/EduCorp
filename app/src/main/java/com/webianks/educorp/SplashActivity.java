package com.webianks.educorp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

                SharedPreferences sp = getSharedPreferences(Constants.LOGIN_SP, Context.MODE_PRIVATE);
                boolean logged = sp.getBoolean(Constants.LOGGED_IN, false);

                if (logged)
                    showDashboard();
                else
                    showLoginRegister();
            }
        }, 1000);

    }

    private void showDashboard() {
        startActivity(new Intent(this, Dashboard.class));
        finish();
    }

    private void showLoginRegister() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
