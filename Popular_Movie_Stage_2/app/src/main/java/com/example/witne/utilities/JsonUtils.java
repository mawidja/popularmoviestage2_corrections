package com.example.witne.utilities;

import com.example.witne.data.Movie;
import com.example.witne.data.MovieReview;
import com.example.witne.data.Trailer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class JsonUtils {

    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w185";

    //for the movie adapter
    public static ArrayList<Movie> parseMovieJson(String json){

        Movie movie;
        String poster_path;
        ArrayList<Movie> movieArrayList = new ArrayList<>();

        try {
            JSONObject baseName = new JSONObject(json);
            JSONArray results = baseName.getJSONArray("results");

            for(int i=0; i<results.length();i++){
                JSONObject jsonObject = results.getJSONObject(i);
                poster_path = IMAGE_BASE_URL + jsonObject.getString("poster_path");
               movie = new Movie(jsonObject.getInt("id"),jsonObject.getString("overview"),jsonObject.getDouble("popularity"),
                                 poster_path,jsonObject.getString("release_date"),
                                 jsonObject.getString("title"),jsonObject.getDouble("vote_average"));
                movieArrayList.add(movie);
            }
            return movieArrayList;

        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    //for the movie trailer adapter
    public static ArrayList<Trailer> parseMovieTrailerJson(String json){

        Trailer trailer;
        ArrayList<Trailer> movieTrailers = new ArrayList<>();

        try {
            JSONObject baseName = new JSONObject(json);
            JSONArray results = baseName.optJSONArray("results");

            for(int i=0; i<results.length();i++){
                JSONObject jsonObject = results.getJSONObject(i);
                trailer = new Trailer(jsonObject.getString("id"),jsonObject.getString("key"),jsonObject.getString("site"),
                          jsonObject.getInt("size"),jsonObject.getString("type"),jsonObject.getString("name"));
                movieTrailers.add(trailer);
            }
            return movieTrailers;

        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    //for the movie reviews adapter
    public static ArrayList<MovieReview> parseMovieReviewJson(String json){

        MovieReview movieReview;
        ArrayList<MovieReview> movieReviews = new ArrayList<>();

        try {
            JSONObject baseName = new JSONObject(json);
            JSONArray results = baseName.optJSONArray("results");

            for(int i=0; i<results.length();i++){
                JSONObject jsonObject = results.getJSONObject(i);
                movieReview = new MovieReview(jsonObject.getString("id"),jsonObject.getString("author"),
                                jsonObject.getString("content"),jsonObject.getString("url"));

                movieReviews.add(movieReview);
            }
            return movieReviews;

        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }
}