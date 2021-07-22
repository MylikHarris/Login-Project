package com.example.logintutorial;

import retrofit2.Call;
import retrofit2.http.*;

public interface InterfaceAPI {

    @POST("user.accounts/checkLogin")
    Call<String> checkLogin(@Header("Username and Password") String authToken);

    @POST("account/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
}
