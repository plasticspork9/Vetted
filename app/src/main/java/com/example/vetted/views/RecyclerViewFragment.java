package com.example.vetted.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vetted.R;
import com.example.vetted.controller.RecyclerViewAdapter;
import com.example.vetted.model.Result;
import com.example.vetted.modells.Businesses;
import com.example.vetted.network.RetrofitSingleton;
import com.example.vetted.network.YelpServiceCall;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class RecyclerViewFragment extends Fragment {
    private RecyclerView recyclerView;
    private static final String LOGTAG = "TAGTAGTAG";
    private RecyclerViewAdapter adapter;
    private List<Businesses> businessesList = new ArrayList<>();
    private OnFragmentInteractionListener mListener;

    public RecyclerViewFragment() {

    }


    public static RecyclerViewFragment newInstance() {
        RecyclerViewFragment fragment = new RecyclerViewFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        Retrofit retrofit = RetrofitSingleton.getInstance();
        retrofit.create(YelpServiceCall.class)
                .getBusinessSearch("animal hospital", -73.935242, 40.730610)
                .enqueue(new Callback<List<Businesses>>() {
                    @Override
                    public void onResponse(Call<List<Businesses>> call, Response<List<Businesses>> response) {
                        Log.d(LOGTAG, "OnResponse: " + response.body().get(0).getName());
                        Log.d(LOGTAG, "OnResponse: " + response.body().get(0).getUrl());

                        businessesList.addAll(response.body());

                        Log.d(LOGTAG, "Size " + businessesList.size());

                        adapter = new RecyclerViewAdapter(businessesList, mListener);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        adapter.setObjects(businessesList);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setHasFixedSize(true);
                    }

                    @Override
                    public void onFailure(Call<List<Businesses>> call, Throwable t) {
                        Log.d(LOGTAG, "onFailure: " + t.toString());
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_recycler_view, container, false);
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
