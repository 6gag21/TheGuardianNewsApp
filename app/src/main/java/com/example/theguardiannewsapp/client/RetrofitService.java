package com.example.theguardiannewsapp.client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private final static String BASE_URL = "https://content.guardianapis.com/";

    private static NewsApi mApiClient;

    public static NewsApi getApiClient(){
        if(mApiClient == null){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            mApiClient = retrofit.create(NewsApi.class);
        }
        return mApiClient;
    }
}
