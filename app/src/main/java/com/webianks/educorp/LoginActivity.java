package com.webianks.educorp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webianks.educorp.data.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EduCorpApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        EduCorpApi eduCorpApi = retrofit.create(EduCorpApi.class);
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
            Toast.makeText(this, login.getApi_key(), Toast.LENGTH_SHORT).show();

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

    @Override
    public void onFailure(Call<Login> call, Throwable t) {

        if (progressDialog != null)
            progressDialog.dismiss();

        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show();
    }
}
