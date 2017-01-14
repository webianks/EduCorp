package com.webianks.educorp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findViewById(R.id.loginBT).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
