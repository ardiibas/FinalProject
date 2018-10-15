package com.example.thinkpadx230.finalproject.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thinkpadx230.finalproject.R;

public class ViewHolderMovie extends RecyclerView.ViewHolder {

    public ImageView ivPoster;
    public TextView tvTitle, tvDesc, tvDate, tvVote;
    public CardView cardView;

    public ViewHolderMovie(@NonNull View itemView) {
        super(itemView);

        ivPoster = itemView.findViewById(R.id.card_image);
        tvTitle = itemView.findViewById(R.id.card_title);
        tvDesc = itemView.findViewById(R.id.card_description);
        tvDate = itemView.findViewById(R.id.card_date);
        tvVote = itemView.findViewById(R.id.card_vote);
        cardView = itemView.findViewById(R.id.card_view);
    }
}
