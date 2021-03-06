package com.webianks.educorp.api;

import com.webianks.educorp.model.GeneralResponse;
import com.webianks.educorp.model.Login;
import com.webianks.educorp.model.Profile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by R Ankit on 14-01-2017.
 */

public interface EduCorpApi {

    String BASE_URL = "http://hackerearth.0x10.info";

    @GET("/api/educorp/auth?query=login")
    Call<Login> loginUser(@Query("type") String type, @Query("email") String email, @Query("password") String password);

    @GET("/api/educorp/auth?query=register")
    Call<GeneralResponse> registerUser(@Query("name") String name,
                                       @Query("email") String email,
                                       @Query("password") String password,
                                       @Query("type") String type);



    @GET("/api/educorp/profile?query=list")
    Call<Profile> getProfile(@Query("api_key") String api_key);


    @GET("/api/educorp/profile?query=edit")
    Call<GeneralResponse> updateProfile(@Query("address") String address,
                                        @Query("zipcode") String zipcode,
                                        @Query("api_key") String api_key);



}
