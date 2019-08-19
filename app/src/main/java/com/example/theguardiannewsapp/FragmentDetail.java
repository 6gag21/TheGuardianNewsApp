package com.example.theguardiannewsapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.theguardiannewsapp.models.Result;


public class FragmentDetail extends Fragment {

    Result mResult;

    public FragmentDetail(){
    }


    ImageView mImg;
    TextView mTitle;
    TextView mDescr;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.detail_fragment, container, false);
        init(v);
        Bundle b = getArguments();
        if(b != null){
            mImg.setTransitionName(b.getString("Transition Name"));
            mResult = b.getParcelable("My Result");
            mTitle.setText(mResult.getTitle());
            mDescr.setText(mResult.getFields().getDescription());
            Glide.with(getContext())
                    .load(mResult.getFields().getImgUrl())
                    .into(mImg);
        }
        v.setOnClickListener(null);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void init(View view){
        mImg = view.findViewById(R.id.fragment_img);
        mTitle = view.findViewById(R.id.fragment_title);
        mDescr = view.findViewById(R.id.fragment_description);
    }
}
