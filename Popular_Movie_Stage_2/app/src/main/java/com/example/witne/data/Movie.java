package com.example.witne.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "movie")
public class Movie implements Parcelable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int movieId;
    private String title;
    private String release_date;
    private double popularity;
    private double vote_average;
    private String poster_path;
    private String movie_overview;

    @Ignore
    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w185";

    //No args constructor
    @Ignore
    public Movie(){

    }

    //Args constructor
    public Movie(int movieId, String title, String release_date, double popularity,
                     double vote_average, String poster_path, String movie_overview){
        this.movieId = movieId;
        this.title = title;
        this.release_date = release_date;
        this.popularity = popularity;
        this.vote_average = vote_average;
        this.poster_path = IMAGE_BASE_URL + poster_path;
        this.movie_overview = movie_overview;
    }

    //De-parcel object
    @Ignore
    private Movie(Parcel in){
        movieId = in.readInt();
        title = in.readString();
        release_date = in.readString();
        popularity = in.readDouble();
        vote_average = in.readDouble();
        poster_path = in.readString();
        movie_overview = in.readString();
    }

    @Override
    @Ignore
    public int describeContents(){
        return 0;
    }

    @Override
    @Ignore
    public void writeToParcel(Parcel dest, int flag){
        dest.writeInt(movieId);
        dest.writeString(title);
        dest.writeString(release_date);
        dest.writeDouble(popularity);
        dest.writeDouble(vote_average);
        dest.writeString(poster_path);
        dest.writeString(movie_overview);
    }

    //Creator]
    @Ignore
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public Movie createFromParcel(Parcel in){
            return new Movie(in);
        }
        public Movie[] newArray(int size){
            return new Movie[size];
        }
    };

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = IMAGE_BASE_URL + poster_path;
    }

    public String getMovie_overview() {
        return movie_overview;
    }

    public void setMovie_overview(String movie_overview) {
        this.movie_overview = movie_overview;
    }

}
