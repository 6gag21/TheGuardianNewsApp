package com.example.theguardiannewsapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.theguardiannewsapp.R;

public class NewsViewHolder extends RecyclerView.ViewHolder {

    private TextView mAuthor;
    private TextView mDate;
    private TextView mTitle;
    private TextView mSection;
    private ImageView mImg;

    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);

        mAuthor = itemView.findViewById(R.id.author);
        mDate = itemView.findViewById(R.id.date);
        mTitle = itemView.findViewById(R.id.title);
        mImg = itemView.findViewById(R.id.img);
        mSection = itemView.findViewById(R.id.section);
    }

    public TextView getAuthor() {
        return mAuthor;
    }

    public TextView getDate() {
        return mDate;
    }

    public TextView getTitle() {
        return mTitle;
    }

    public ImageView getImg() {
        return mImg;
    }

    public TextView getSection() {
        return mSection;
    }
}
