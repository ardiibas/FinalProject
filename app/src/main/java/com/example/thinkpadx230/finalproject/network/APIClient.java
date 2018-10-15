package com.example.thinkpadx230.finalproject.network;

import com.example.thinkpadx230.finalproject.model.detail.Detail;
import com.example.thinkpadx230.finalproject.model.list.Movie;
import com.example.thinkpadx230.finalproject.model.upcoming.Upcoming;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIClient {

    //Now Playing
    @GET("3/movie/now_playing")
    Call<Upcoming> getNowPlaying(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") String page)
    ;

    // Get Upcoming
    @GET("3/movie/upcoming")
    Call<Upcoming> getUpcoming(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") String page)
    ;

    // Get detail a movie
    @GET("3/movie/{movie_id}")
    Call<Detail> getDetail(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key,
            @Query("language") String language
    );

    // Search Movie
    @GET("3/search/movie")
    Call<Movie> getSearch(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("query") String query
    );
}
