package com.example.thinkpadx230.finalproject.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thinkpadx230.finalproject.R;
import com.example.thinkpadx230.finalproject.model.detail.Detail;
import com.example.thinkpadx230.finalproject.network.APIClient;
import com.example.thinkpadx230.finalproject.network.ServiceGenerator;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private final String api_key = "6cbbb575d03419c61482de70c8706aae";
    private final String language = "en-US";

    private int id;

    private ImageView imageView;
    private TextView tvTitle, tvTagline, tvOver, tvRelease;

    private final APIClient client = ServiceGenerator.createService(APIClient.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        id = getIntent().getIntExtra("id", 0);

        imageView = findViewById(R.id.detail_image);
        tvTitle = findViewById(R.id.detail_title);
        tvTagline = findViewById(R.id.detail_tagline);
        tvOver = findViewById(R.id.detail_overview);
        tvRelease = findViewById(R.id.detail_release);

        getDetails();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    private void getDetails() {

        Call<Detail> detailCall = client.getDetail(id, api_key, language);

        detailCall.enqueue(new Callback<Detail>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<Detail> call, @NonNull Response<Detail> response) {

                assert response.body() != null;

                Glide.with(getApplicationContext())
                        .load("http://image.tmdb.org/t/p/w185" + response.body()
                                .getPosterPath()).into(imageView);

                tvTitle.setText(response.body().getTitle());
                tvTagline.setText(response.body().getTagline());
                tvOver.setText(response.body().getOverview());
                tvRelease.setText("Release date : " + response.body().getReleaseDate());
            }

            @Override
            public void onFailure(@NonNull Call<Detail> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
