package com.example.theguardiannewsapp.utils;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.theguardiannewsapp.client.ApiClient;
import com.example.theguardiannewsapp.client.RetrofitService;
import com.example.theguardiannewsapp.models.News;
import com.example.theguardiannewsapp.models.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.theguardiannewsapp.utils.Constants.API_KEY;
import static com.example.theguardiannewsapp.utils.Constants.FIELDS;

public class NewsRepository {


    private static NewsRepository instance;
    private MediatorLiveData<List<Result>> mResult = new MediatorLiveData<>();
    private ApiClient mApiClient;
    private int mPageNumber;

    public static NewsRepository getInstance(){
        if(instance == null){
            instance = new NewsRepository();
        }
        return instance;
    }

    private NewsRepository(){
        mApiClient = ApiClient.getInstance();
        init();
    }

    private void init(){
        LiveData<List<Result>> apiResult = mApiClient.getResults();
        mResult.addSource(apiResult, new Observer<List<Result>>() {
            @Override
            public void onChanged(@Nullable List<Result> results) {
                if(results != null){
                    mResult.setValue(results);
                }
            }
        });
    }

    public LiveData<List<Result>> getResult(){
        return mResult;
    }

    public void requestApiClient(int pageNumber, Context context){

        mPageNumber = pageNumber;
        mApiClient.request(pageNumber, context);
    }

    public void searchNextPage(Context context){
        mPageNumber++;
        mApiClient.request(mPageNumber, context);
    }

}
