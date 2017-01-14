package com.webianks.educorp.screens;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.webianks.educorp.R;

public class ProfileActivity extends AppCompatActivity {

    private String type;
    private LinearLayout tutorLayout;
    private LinearLayout parentLayout;

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

        Toast.makeText(this, type, Toast.LENGTH_SHORT).show();

        if (type.equals("Tutor")) {

            parentLayout.setVisibility(View.GONE);

        } else if (type.equals("Parent")) {

            tutorLayout.setVisibility(View.GONE);
        }

    }

    private void init() {

        type = getIntent().getStringExtra("type");
        parentLayout = (LinearLayout) findViewById(R.id.parentLayout);
        tutorLayout = (LinearLayout) findViewById(R.id.tutorLayout);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}
