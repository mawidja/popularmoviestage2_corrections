package com.example.witne.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "favourite_movie")
public class FavouriteMovie {

    @PrimaryKey
    private int movieId;
    private String movie_overview;
    private double popularity;
    private String poster_path;
    private String release_date;
    private String title;
    private double vote_average;


    //Args constructor
    public FavouriteMovie(int movieId, String movie_overview, double popularity, String poster_path,
                 String release_date, String title, double vote_average ){
        this.movieId = movieId;
        this.movie_overview = movie_overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.title = title;
        this.vote_average = vote_average;
    }

    public int getMovieId() { return movieId; }
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovie_overview() {
        return movie_overview;
    }
    public void setMovie_overview(String movie_overview) {
        this.movie_overview = movie_overview;
    }

    public double getPopularity() {
        return popularity;
    }
    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }
    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }
    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public double getVote_average() {
        return vote_average;
    }
    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }
}
