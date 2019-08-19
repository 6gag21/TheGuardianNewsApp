package com.example.theguardiannewsapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.theguardiannewsapp.adapter.NewsAdapter;
import com.example.theguardiannewsapp.models.Result;
import com.example.theguardiannewsapp.utils.CustomScrollListener;
import com.example.theguardiannewsapp.viewmodels.MainFragmentViewModel;

import java.util.List;

public class MainFragment extends Fragment implements NewsAdapter.OnItemClickListener {

    private NewsAdapter mNewsAdapter;
    private CustomScrollListener mScrollListener;
    private MainFragmentViewModel mMainFragmentViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMainFragmentViewModel = ViewModelProviders.of(this).get(MainFragmentViewModel.class);
        mMainFragmentViewModel.requestApiClient(1, getContext());
        initRecyclerView(view);
        mMainFragmentViewModel.getResults().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(@Nullable List<Result> news) {
                if(news != null) {
                    mNewsAdapter.setNewsList(news);
                    mScrollListener.setLoading(false);
                }
            }
        });

    }

    private void initRecyclerView(View view){
        mNewsAdapter = new NewsAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mScrollListener = new CustomScrollListener(layoutManager) {
            @Override
            public void onLoadMore() {
                mMainFragmentViewModel.searchNextPage(getContext());
            }
        };
        RecyclerView recyclerView = view.findViewById(R.id.rv_main);
        recyclerView.addOnScrollListener(mScrollListener);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mNewsAdapter);
    }


    private void openFragmentInContainer(ImageView img, Result result, String transition){
        FragmentDetail fragmentDetail = new FragmentDetail();
        Bundle bundle = new Bundle();
        bundle.putString("Transition Name", transition);
        bundle.putParcelable("My Result", result);
        fragmentDetail.setArguments(bundle);

        ((MainActivity) getContext()).openFragmentWithTransition(this, fragmentDetail, img, transition);
    }

    @Override
    public void onItemClicked(ImageView img, Result item, String transition) {
        openFragmentInContainer(img, item, transition);
    }
}
