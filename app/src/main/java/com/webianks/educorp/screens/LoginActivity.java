package com.webianks.educorp.screens;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.webianks.educorp.api.Constants;
import com.webianks.educorp.api.EduCorpApi;
import com.webianks.educorp.R;
import com.webianks.educorp.api.RestClient;
import com.webianks.educorp.model.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, Callback<Login> {


    private EditText emailET;
    private EditText passwordET;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        emailET = (EditText) findViewById(R.id.emailAddressET);
        passwordET = (EditText) findViewById(R.id.passwordET);

        findViewById(R.id.signUpBT).setOnClickListener(this);
        findViewById(R.id.loginNow).setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.signUpBT)

            startActivity(new Intent(this, RegisterActivity.class));

        else {

            String email = emailET.getText().toString();
            String password = passwordET.getText().toString();

            if (email.trim().length() > 0 && password.trim().length() > 0)
                loginNow(email, password);
            else
                Toast.makeText(this, getString(R.string.please_provide), Toast.LENGTH_SHORT).show();
        }

    }

    private void loginNow(String email, String password) {


        setupDialog();

        EduCorpApi eduCorpApi = new RestClient().getApiService();
        Call<Login> loginUser = eduCorpApi.loginUser("0", email, password);

        //asynchronous call
        loginUser.enqueue(this);
    }

    private void setupDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getString(R.string.login));
        progressDialog.setMessage(getString(R.string.please_wait));
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }


    @Override
    public void onResponse(Call<Login> call, Response<Login> response) {

        if (progressDialog != null)
            progressDialog.dismiss();

        if (response.isSuccessful()) {

            Login login = response.body();

            SharedPreferences sp = getSharedPreferences(Constants.LOGIN_SP, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(Constants.API_KEY, login.getApi_key());
            editor.apply();

            gotoDashboard();

        } else {

            switch (response.code()) {

                case Constants.NOT_FOUND:
                    Toast.makeText(this, getString(R.string.not_found), Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void gotoDashboard() {

        SharedPreferences sp = getSharedPreferences(Constants.LOGIN_SP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(Constants.LOGGED_IN, true);
        editor.apply();

        startActivity(new Intent(this, Dashboard.class));

    }

    @Override
    public void onFailure(Call<Login> call, Throwable t) {

        if (progressDialog != null)
            progressDialog.dismiss();

        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show();
    }
}
