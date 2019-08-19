package com.example.theguardiannewsapp.client;

import com.example.theguardiannewsapp.models.News;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    @GET("search")
    Call<News> getJson(@Query("api-key") String apiKey,
                       @Query("show-fields") String fields,
                       @Query("page") int page);
}
