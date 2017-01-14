package com.webianks.educorp.screens;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.webianks.educorp.R;
import com.webianks.educorp.api.Constants;
import com.webianks.educorp.model.Profile;

public class ProfileActivity extends AppCompatActivity {

    private String type;
    private LinearLayout tutorLayout;
    private LinearLayout parentLayout;
    private Profile.ProfileBean profileBean;
    private TextView nameTV;
    private TextView emailAddressTV;
    private TextView typeTV;
    private EditText addressET;
    private EditText zipCodeET;

    private EditText nameET;
    private EditText schoolET;
    private EditText gradeET;

    private EditText subjectsET;
    private EditText bioET;
    private EditText studentTutET;

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

        if (type.equals("Tutor")) {

            parentLayout.setVisibility(View.GONE);

            if (profileBean != null) {

                setBasicProfile();

                if (profileBean.getProfile().size() > 0)
                    setCoreProfile(profileBean.getProfile().get(0));
            }

        } else if (type.equals("Parent")) {

            tutorLayout.setVisibility(View.GONE);
        }

    }

    private void setCoreProfile(Profile.CoreProfileBean coreProfileBean) {

        addressET.setText(coreProfileBean.getAddress());
        zipCodeET.setText(coreProfileBean.getZipcode());

        nameET.setText(coreProfileBean.getStudent_name());
        schoolET.setText(coreProfileBean.getStudent_school());
        gradeET.setText(coreProfileBean.getStudent_grade());
        bioET.setText(coreProfileBean.getBio());

        StringBuilder subjects = new StringBuilder();

        if (coreProfileBean.getSubjects().size()>0){

            for(String subject:coreProfileBean.getSubjects())
                subjects.append(subject);
        }

        StringBuilder tutoring = new StringBuilder();

        if (coreProfileBean.getStudents().size()>0){

            for(String student:coreProfileBean.getStudents())
                tutoring.append(student);
        }


        subjectsET.setText(subjects);
        studentTutET.setText(tutoring);

    }

    private void setBasicProfile() {

        nameTV.setText(profileBean.getName());
        emailAddressTV.setText(profileBean.getEmail());
        typeTV.setText(profileBean.getAccount_type());

    }

    private void init() {

        type = getIntent().getStringExtra("type");
        parentLayout = (LinearLayout) findViewById(R.id.parentLayout);
        tutorLayout = (LinearLayout) findViewById(R.id.tutorLayout);


        nameTV = (TextView) findViewById(R.id.name);
        emailAddressTV = (TextView) findViewById(R.id.email);
        typeTV = (TextView) findViewById(R.id.type);

        addressET = (EditText) findViewById(R.id.addressET);
        zipCodeET = (EditText) findViewById(R.id.zipCodeET);

        nameET = (EditText) findViewById(R.id.nameET);
        schoolET = (EditText) findViewById(R.id.schoolET);
        gradeET = (EditText) findViewById(R.id.gradeET);

        subjectsET = (EditText) findViewById(R.id.subjectsET);
        bioET = (EditText) findViewById(R.id.bioEt);
        studentTutET = (EditText) findViewById(R.id.tutoringET);

        SharedPreferences sp = getSharedPreferences(Constants.PROFILE_SP, Context.MODE_PRIVATE);
        String profile_json = sp.getString(Constants.PROFILE_DATA, null);

        if (profile_json != null)
            profileBean = new Gson().fromJson(profile_json, Profile.ProfileBean.class);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}
