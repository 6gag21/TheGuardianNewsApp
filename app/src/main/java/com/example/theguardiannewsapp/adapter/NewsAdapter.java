package com.example.theguardiannewsapp.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.theguardiannewsapp.R;
import com.example.theguardiannewsapp.models.Result;
import com.example.theguardiannewsapp.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private List<Result> newsList;
    private OnItemClickListener mOnItemClickListener;

    public NewsAdapter (OnItemClickListener onItemClickListener){
        newsList = new ArrayList<>();
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item, viewGroup, false);

        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsViewHolder newsViewHolder, final int i) {
        final Result item = newsList.get(i);
        newsViewHolder.getAuthor().setText(item.getFields().getAuthor());
        newsViewHolder.getTitle().setText(item.getTitle());
        newsViewHolder.getDate().setText(DateUtil.dateFormat(item.getDate()));
        newsViewHolder.getSection().setText(item.getSection());
        Glide.with(newsViewHolder.itemView.getContext())
                .load(item.getFields().getImgUrl())
                .into(newsViewHolder.getImg());
        ViewCompat.setTransitionName(newsViewHolder.getImg(), "transition" + i);
        newsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClicked(newsViewHolder.getImg(), item, newsViewHolder.getImg().getTransitionName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public interface OnItemClickListener{
        void onItemClicked(ImageView img, Result item, String transition);
    }

    public void setNewsList(List<Result> data){
        this.newsList.addAll(data);
        notifyDataSetChanged();
    }


}
