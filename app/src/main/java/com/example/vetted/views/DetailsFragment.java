package com.example.vetted.views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vetted.R;


public class DetailsFragment extends Fragment {


    public DetailsFragment() {

    }



    public static DetailsFragment newInstance(String image, String [] otherImages, String name,
                                              String alias, String phoneNumber, String rating,
                                              Boolean open, String price, String url) {
        DetailsFragment fragment = new DetailsFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_details, container, false);
    }

}
