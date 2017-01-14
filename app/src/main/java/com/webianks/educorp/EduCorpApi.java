package com.webianks.educorp;

import com.webianks.educorp.data.Login;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by R Ankit on 14-01-2017.
 */

public interface EduCorpApi {

    public static String BASE_URL = "http://hackerearth.0x10.info/api/educorp/";

    @GET("/auth?query=login")
    Call<Login> loginUser(@Query("type") String type, @Query("email") String email, @Query("password") String password);


}
