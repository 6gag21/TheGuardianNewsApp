package com.example.theguardiannewsapp.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.theguardiannewsapp.models.Result;
import com.example.theguardiannewsapp.utils.NewsRepository;

import java.util.List;

public class MainFragmentViewModel extends ViewModel {


    private NewsRepository mRepository;

    public MainFragmentViewModel(){
        mRepository = NewsRepository.getInstance();
    }

    public LiveData<List<Result>> getResults(){
        return mRepository.getResult();
    }

   public void requestApiClient(int pageNumber, Context context){
        mRepository.requestApiClient(pageNumber, context);
   }

    public void searchNextPage(Context context){
        mRepository.searchNextPage(context);
    }
}
