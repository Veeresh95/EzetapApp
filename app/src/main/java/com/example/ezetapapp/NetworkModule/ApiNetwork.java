package com.example.ezetapapp.NetworkModule;

import com.example.ezetapapp.PojoClasses.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiNetwork {

    @GET("mobileapps/android_assignment.json")
    Call<Response> getJSON();
}
