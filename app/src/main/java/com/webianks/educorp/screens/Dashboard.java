package com.webianks.educorp.screens;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.webianks.educorp.api.Constants;
import com.webianks.educorp.api.EduCorpApi;
import com.webianks.educorp.R;
import com.webianks.educorp.api.RestClient;
import com.webianks.educorp.model.Profile;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashboard extends AppCompatActivity implements Callback<Profile>, View.OnClickListener {


    private String api_key;
    private TextView nameTV;
    private TextView emailAddressTV;
    private TextView typeTV;
    private ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);

        init();
        getProfile();

    }


    private void getProfile() {

        showProgressBar();

        EduCorpApi eduCorpApi = new RestClient().getApiService();
        Call<Profile> profileCall = eduCorpApi.getProfile(api_key);

        //asynchronous call
        profileCall.enqueue(this);
    }

    private void showProgressBar() {

        progressdialog = new ProgressDialog(this);
        progressdialog.setMessage(getString(R.string.please_wait));
        progressdialog.setCancelable(false);
        progressdialog.show();
    }

    private void init() {

        SharedPreferences sp = getSharedPreferences(Constants.LOGIN_SP, Context.MODE_PRIVATE);
        api_key = sp.getString(Constants.API_KEY, null);


        nameTV = (TextView) findViewById(R.id.name);
        emailAddressTV = (TextView) findViewById(R.id.email);
        typeTV = (TextView) findViewById(R.id.type);

        findViewById(R.id.profile_container).setOnClickListener(this);


    }

    @Override
    public void onResponse(Call<Profile> call, Response<Profile> response) {

        if (progressdialog != null)
            progressdialog.dismiss();

        if (response.isSuccessful()) {

            Profile profile = response.body();
            List<Profile.ProfileBean> profileBean = profile.getProfile();

            saveInPreferences(profileBean);

            if (profileBean.size() > 0) {

                String name = profileBean.get(0).getName();
                String email = profileBean.get(0).getEmail();
                String type = profileBean.get(0).getAccount_type();

                setProfileData(name, email, type);
            }

        } else {

        }
    }

    private void saveInPreferences(List<Profile.ProfileBean> profileBean) {

        SharedPreferences sp = getSharedPreferences(Constants.PROFILE_SP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(Constants.PROFILE_DATA, new Gson().toJson(profileBean.get(0)));
        editor.apply();
    }

    private void setProfileData(String name, String email, String type) {

        nameTV.setText(name);
        emailAddressTV.setText(email);
        typeTV.setText(type);

    }

    @Override
    public void onFailure(Call<Profile> call, Throwable t) {

        if (progressdialog != null)
            progressdialog.dismiss();
    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.profile_container) {

            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("type", typeTV.getText());
            intent.putExtra("api_key", api_key);
            startActivity(intent);
        }

    }


}
