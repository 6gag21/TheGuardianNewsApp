package com.example.theguardiannewsapp.client;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.theguardiannewsapp.R;
import com.example.theguardiannewsapp.models.News;
import com.example.theguardiannewsapp.models.Result;
import com.example.theguardiannewsapp.utils.NetworkUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.theguardiannewsapp.utils.Constants.API_KEY;
import static com.example.theguardiannewsapp.utils.Constants.FIELDS;

public class ApiClient {

    private static ApiClient instance;
    private MutableLiveData<List<Result>> mResults;


    public static ApiClient getInstance(){
        if(instance == null){
            instance = new ApiClient();
        }
        return instance;
    }

    private ApiClient(){
        mResults = new MutableLiveData<>();
    }

    public LiveData<List<Result>> getResults(){
        return mResults;
    }

    public void request(int page, final Context context){
        final ArrayList<Result> results = new ArrayList<>();

        if(NetworkUtils.isNetworkAvailable(context)){
            Call<News> call = RetrofitService.getApiClient().getJson(API_KEY, FIELDS, page);
            call.enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    News news = response.body();
                    if(news != null){
                        results.addAll(news.getResponse().getResults());
                        mResults.postValue(results);
                    }
                }

                @Override
                public void onFailure(Call<News> call, Throwable t) {
                    Log.v("TAG", "Failure : " + t.toString());
                }
            });
        } else {
            Log.v("TAG", "No network connection");
        }


    }



}
