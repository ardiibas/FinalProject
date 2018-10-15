package com.example.thinkpadx230.finalproject.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thinkpadx230.finalproject.R;
import com.example.thinkpadx230.finalproject.adapter.AdapterMovie;
import com.example.thinkpadx230.finalproject.model.list.ListMovie;
import com.example.thinkpadx230.finalproject.model.upcoming.Upcoming;
import com.example.thinkpadx230.finalproject.network.APIClient;
import com.example.thinkpadx230.finalproject.network.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpComing extends Fragment {

    private List<ListMovie> listMovies = new ArrayList<>();
    private AdapterMovie adapterMovie;

    public int id;
    public String image_path, title, overview, release_date;
    public Double vote;

    private final APIClient client = ServiceGenerator.createService(APIClient.class);

    public UpComing() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_up_coming, container, false
        );

        RecyclerView recyclerView = view.findViewById(R.id.up_recycler);
        adapterMovie = new AdapterMovie(getActivity(), listMovies);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterMovie);

        getUpComing();

        return view;
    }

    private void getUpComing() {
        String api_key = "6cbbb575d03419c61482de70c8706aae";
        String language = "en-US";
        String page = "1";
        Call<Upcoming> comingCall = client.getUpcoming(
                api_key,
                language,
                page
        );

        comingCall.enqueue(new Callback<Upcoming>() {
            @Override
            public void onResponse(@NonNull Call<Upcoming> call, @NonNull Response<Upcoming> response) {

                assert response.body() != null;

                for (int i = 0; i < response.body().getResults().size(); i++) {
                    id = response.body().getResults().get(i).getId();
                    image_path = response.body().getResults().get(i).getPosterPath();
                    title = response.body().getResults().get(i).getTitle();
                    overview = response.body().getResults().get(i).getOverview();
                    release_date = response.body().getResults().get(i).getReleaseDate();
                    vote = response.body().getResults().get(i).getVoteAverage();

                    ListMovie movie = new ListMovie(
                            id, image_path, title, overview, release_date, vote
                    );

                    listMovies.add(movie);
                }

                adapterMovie.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NonNull Call<Upcoming> call, @NonNull Throwable t) {

            }
        });
    }
}
