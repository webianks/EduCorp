package com.webianks.educorp.screens;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.webianks.educorp.R;

public class ProfileActivity extends AppCompatActivity {

    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);


        init();
        setupAccordToType();

    }

    private void setupAccordToType() {

        if (type.equals("Tutor")){

        }else if(type.equals("Parent")){

        }

    }

    private void init() {
        type = getIntent().getStringExtra("type");
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}
