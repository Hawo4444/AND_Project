package com.example.and_project.api;

import com.example.and_project.domain.ApiBody;
import com.example.and_project.domain.MealsList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiEndpoint
{
    //This is the type of the content being sent and received
    String CONTENT_TYPE = "Content-Type:application/json";
    //This is the application ID that is being sent with each API request
    String APPLICATION_ID = "x-app-id:c369792c";
    //This is the application key used to authenticate each request
    String APPLICATION_KEY = "x-app-key:393131f5a5db246d4e4a532947e09caf";

    @POST("v2/natural/nutrients")
    @Headers({CONTENT_TYPE, APPLICATION_ID, APPLICATION_KEY})
    Call<MealsList> getFoodInformation(@Body ApiBody apiBody);
}
