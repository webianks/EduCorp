package com.webianks.educorp.screens;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webianks.educorp.Constants;
import com.webianks.educorp.EduCorpApi;
import com.webianks.educorp.R;
import com.webianks.educorp.data.Profile;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Dashboard extends AppCompatActivity implements Callback<Profile>, View.OnClickListener {


    private String api_key;
    private TextView nameTV;
    private TextView emailAddressTV;
    private TextView typeTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);

        init();
        getProfile();

    }


    private void getProfile() {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EduCorpApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        EduCorpApi eduCorpApi = retrofit.create(EduCorpApi.class);
        Call<Profile> profileCall = eduCorpApi.getProfile(api_key);

        //asynchronous call
        profileCall.enqueue(this);
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

        if (response.isSuccessful()) {

            Profile profile = response.body();
            List<Profile.ProfileBean> profileBean = profile.getProfile();

            if (profileBean.size() > 0) {

                String name = profileBean.get(0).getName();
                String email = profileBean.get(0).getEmail();
                String type = profileBean.get(0).getAccount_type();

                setProfileData(name, email, type);

            }


        } else {

        }
    }

    private void setProfileData(String name, String email, String type) {

        nameTV.setText(name);
        emailAddressTV.setText(email);
        typeTV.setText(type);

    }

    @Override
    public void onFailure(Call<Profile> call, Throwable t) {

    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.profile_container) {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("type",typeTV.getText());
            startActivity(intent);

        }

    }


}
