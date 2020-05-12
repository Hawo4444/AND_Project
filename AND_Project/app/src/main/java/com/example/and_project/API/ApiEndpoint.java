package com.example.and_project.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ApiEndpoint
{
    //This is the application IDthat is being sent with each API request
    String APPLICATION_ID = "c369792c";
    //This is the application key used to authenticate each request
    String APPLICATION_KEY = "393131f5a5db246d4e4a532947e09caf";

    @GET("v2/search/instant?query={foodDetails}")//detailed = true
    //@Headers({"x-app-id", APPLICATION_ID},{"x-app-PkrojectD"})
    Call<FoodInformationFromAPI> getFoodInformation(@Path("foodDetails") String foodDetails);
}
