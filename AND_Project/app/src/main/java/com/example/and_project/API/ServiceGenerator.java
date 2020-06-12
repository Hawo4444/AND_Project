package com.example.and_project.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator
{
    private static final String BASE_URL = "https://trackapi.nutritionix.com/";

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static ApiEndpoint apiEndpoint = retrofit.create(ApiEndpoint.class);

    public static ApiEndpoint getApiEndpoint()
    {
        return apiEndpoint;
    }
}
