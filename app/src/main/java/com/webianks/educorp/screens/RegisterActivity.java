package com.webianks.educorp.screens;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.webianks.educorp.api.EduCorpApi;
import com.webianks.educorp.model.GeneralResponse;
import com.webianks.educorp.R;
import com.webianks.educorp.api.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, Callback<GeneralResponse> {


    private EditText nameEt;
    private EditText emailET;
    private EditText passwordET;
    private RadioGroup typeRG;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameEt = (EditText) findViewById(R.id.nameET);
        emailET = (EditText) findViewById(R.id.emailAddressET);
        passwordET = (EditText) findViewById(R.id.passwordET);
        typeRG = (RadioGroup) findViewById(R.id.typeRG);


        findViewById(R.id.loginBT).setOnClickListener(this);
        findViewById(R.id.registerNow).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.loginBT)
            startActivity(new Intent(this, LoginActivity.class));

        else {

            String name = nameEt.getText().toString();
            String email = emailET.getText().toString();
            String password = passwordET.getText().toString();
            String type = null;

            int checked = typeRG.getCheckedRadioButtonId();

            if (checked == R.id.parent)
                type = "0";
            else if (checked == R.id.tutor)
                type = "1";

            if (name.trim().length() > 0 && email.trim().length() > 0 && password.trim().length() > 0 & type != null) {

                registerNow(name,email,password,type);

            }


        }

    }

    private void registerNow(String name, String email, String password, String type) {

        setupDialog();

        EduCorpApi eduCorpApi = new RestClient().getApiService();
        Call<GeneralResponse> generalResponseCall = eduCorpApi.registerUser(name,email,password,type);

        //asynchronous call
        generalResponseCall.enqueue(this);
    }

    private void setupDialog() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getString(R.string.register));
        progressDialog.setMessage(getString(R.string.please_wait));
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }

    @Override
    public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {

        if (progressDialog != null)
            progressDialog.dismiss();

        if (response.isSuccessful()) {

            GeneralResponse generalResponse = response.body();
            Toast.makeText(this, generalResponse.getMessage(), Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, response.message(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onFailure(Call<GeneralResponse> call, Throwable t) {

        if (progressDialog != null)
            progressDialog.dismiss();

        Toast.makeText(this, getString(R.string.error_register), Toast.LENGTH_SHORT).show();

    }
}
