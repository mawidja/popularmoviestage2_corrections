package com.example.witne.data;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieReview implements Parcelable {

    public String getMovieReviewId() {
        return movieReviewId;
    }

    public void setMovieReviewId(String movieReviewId) {
        this.movieReviewId = movieReviewId;
    }

    public String getMovieReviewAuthor() {
        return movieReviewAuthor;
    }

    public void setMovieReviewAuthor(String movieReviewAuthor) {
        this.movieReviewAuthor = movieReviewAuthor;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public String getReviewUrl() {
        return reviewUrl;
    }

    public void setReviewUrl(String reviewUrl) {
        this.reviewUrl = reviewUrl;
    }

    private String movieReviewId;
    private String movieReviewAuthor;
    private String reviewContent;
    private String reviewUrl;


    //No-Args constructor
    public MovieReview() {
    }

    //arg constructor
    public MovieReview(String movieReviewId,String movieReviewAuthor,String reviewContent,String reviewUrl){
        this.movieReviewId = movieReviewId;
        this.movieReviewAuthor = movieReviewAuthor;
        this.reviewContent = reviewContent;
        this.reviewUrl = reviewUrl;
    }

    //De-parcel object
    private MovieReview(Parcel in){
        movieReviewId = in.readString();
        movieReviewAuthor = in.readString();
        reviewContent = in.readString();
        reviewUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(movieReviewId);
        dest.writeString(movieReviewAuthor);
        dest.writeString(reviewContent);
        dest.writeString(reviewUrl);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public MovieReview createFromParcel(Parcel in){
            return new MovieReview(in);
        }
        public MovieReview[] newArray(int size){
            return new MovieReview[size];
        }
    };
}
