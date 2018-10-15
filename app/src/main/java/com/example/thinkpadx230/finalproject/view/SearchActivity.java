package com.example.thinkpadx230.finalproject.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.thinkpadx230.finalproject.R;
import com.example.thinkpadx230.finalproject.adapter.AdapterMovie;
import com.example.thinkpadx230.finalproject.model.list.ListMovie;
import com.example.thinkpadx230.finalproject.model.list.Movie;
import com.example.thinkpadx230.finalproject.network.APIClient;
import com.example.thinkpadx230.finalproject.network.ServiceGenerator;
import com.example.thinkpadx230.finalproject.util.ItemClick;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements MaterialSearchBar.OnSearchActionListener {

    private List<ListMovie> listMovies = new ArrayList<>();
    private AdapterMovie adapterMovie;

    public int id;
    public String image_path, title, overview, release_date;
    public Double vote;

    private final APIClient client = ServiceGenerator.createService(APIClient.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        MaterialSearchBar searchBar = findViewById(R.id.search_searchbar);
        RecyclerView recyclerView = findViewById(R.id.search_recycler);
        adapterMovie = new AdapterMovie(getApplicationContext(), listMovies);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterMovie);

        searchBar.setOnSearchActionListener(this);

        recyclerView.addOnItemTouchListener(new ItemClick(getApplicationContext(), (view1, position) -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("id", listMovies.get(position).getId());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }));
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {

    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
        getSearchData(String.valueOf(text));
    }

    @Override
    public void onButtonClicked(int buttonCode) {

    }

    private void getSearchData(String s) {

        String api_key = "6cbbb575d03419c61482de70c8706aae";
        String language = "en-US";

        Call<Movie> movieCall = client.getSearch(
                api_key,
                language,
                s
        );

        movieCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {

                assert response.body() != null;

                for (int i = 0; i < response.body().getResults().size(); i++){
                    id  = response.body().getResults().get(i).getId();
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
            public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {

            }
        });
    }
}
