package com.example.thinkpadx230.finalproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.thinkpadx230.finalproject.R;
import com.example.thinkpadx230.finalproject.model.list.ListMovie;

import java.util.List;

public class AdapterMovie extends RecyclerView.Adapter<ViewHolderMovie> {

    private Context context;
    private List<ListMovie> listMovies;

    public AdapterMovie(Context context, List<ListMovie> listMovies) {
        this.context = context;
        this.listMovies = listMovies;
    }

    @NonNull
    @Override
    public ViewHolderMovie onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_list, viewGroup, false
        );

        return new ViewHolderMovie(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMovie viewHolderMovie, int i) {
        ListMovie movie = listMovies.get(i);

        String imgPath = "http://image.tmdb.org/t/p/w185" + movie.getImage();
        String release = "Release : " + movie.getRelease();
        String vote = "Vote : " +String.valueOf(movie.getVote());

        viewHolderMovie.tvTitle.setText(movie.getTitle());
        viewHolderMovie.tvDesc.setText(movie.getOverview());
        viewHolderMovie.tvDate.setText(release);
        viewHolderMovie.tvVote.setText(vote);

        Glide.with(context)
                .load(imgPath)
                .into(viewHolderMovie.ivPoster);
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }
}
