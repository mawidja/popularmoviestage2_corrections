package com.example.witne.data;

import android.os.Parcel;
import android.os.Parcelable;


public class Movie implements Parcelable {

    private int movieId;
    private String movie_overview;
    private double popularity;
    private String poster_path;
    private String release_date;
    private String title;
    private double vote_average;

    //No args constructor
    public Movie(){

    }

    //Args constructor
    public Movie(int movieId, String movie_overview, double popularity, String poster_path,
                 String release_date, String title, double vote_average ){
        this.movieId = movieId;
        this.movie_overview = movie_overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.title = title;
        this.vote_average = vote_average;
    }

    //De-parcel object
    private Movie(Parcel in){
        movieId = in.readInt();
        movie_overview = in.readString();
        popularity = in.readDouble();
        poster_path = in.readString();
        release_date = in.readString();
        title = in.readString();
        vote_average = in.readDouble();
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flag){
        dest.writeInt(movieId);
        dest.writeString(movie_overview);
        dest.writeDouble(popularity);
        dest.writeString(poster_path);
        dest.writeString(release_date);
        dest.writeString(title);
        dest.writeDouble(vote_average);
    }

    //Creator]
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public Movie createFromParcel(Parcel in){
            return new Movie(in);
        }
        public Movie[] newArray(int size){
            return new Movie[size];
        }
    };

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
