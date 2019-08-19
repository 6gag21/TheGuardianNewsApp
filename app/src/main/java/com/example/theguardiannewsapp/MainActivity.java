package com.example.theguardiannewsapp;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionInflater;
import android.widget.ImageView;



public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainFragment mainFragment = new MainFragment();
        openFragmentInContainer(mainFragment);
    }

    private void openFragmentInContainer(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    public void openFragmentWithTransition(Fragment current, Fragment newFragment, ImageView img, String transition) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        current.setSharedElementReturnTransition(TransitionInflater.from(this).inflateTransition(R.transition.img_transition));
        current.setExitTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.no_transition));

        newFragment.setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.img_transition));
        newFragment.setEnterTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.no_transition));

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, newFragment);
        fragmentTransaction.addToBackStack(current.getClass().getSimpleName());
        fragmentTransaction.addSharedElement(img, transition);
        fragmentTransaction.commit();
    }
}
