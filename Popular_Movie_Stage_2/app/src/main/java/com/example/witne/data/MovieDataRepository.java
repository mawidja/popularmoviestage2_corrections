package com.example.witne.data;

import android.content.Context;
import android.os.AsyncTask;

import com.example.witne.popularmoviesstage2.MovieAppExecutor;
import com.example.witne.utilities.JsonUtils;
import com.example.witne.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;

public class MovieDataRepository {

    private static MovieDataRepository sInstance;
    private static final Object LOCK = new Object();
    private MovieDao movieDao;

    private MovieDataRepository(Context context) {
        //this.context = context;
        MovieRoomDatabase roomDatabase = MovieRoomDatabase.getInstance(context);
        movieDao = roomDatabase.movieDao();
    }


    public static MovieDataRepository getInstance(Context context){
        if(sInstance == null){
            synchronized (LOCK){
                sInstance = new MovieDataRepository(context.getApplicationContext());
            }
        }return sInstance;
    }

    public LiveData<FavouriteMovie> getFavouriteMovie(int movieId){
        return movieDao.getFavouriteMovie(movieId);
    }

    public LiveData<List<FavouriteMovie>> getAllFavouriteMovies(){
        return movieDao.getAllFavouriteMovies();
    }

    public void insertFavouriteMovie(final FavouriteMovie favouriteMovie) {
        MovieAppExecutor.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if(favouriteMovie != null) {
                    movieDao.insertFavouriteMovie(favouriteMovie);
                }
            }
        });
    }

    public void deleteFavouriteMovie(final FavouriteMovie favouriteMovie){

        MovieAppExecutor.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                movieDao.deleteFavouriteMovie(favouriteMovie);
            }
        });
    }

    public LiveData<List<Trailer>> getMovieTrailers(URL movieSearchURL){
        return new FetchMovieTrailer(movieSearchURL);
    }

    public LiveData<List<MovieReview>> getMovieReviews(URL movieSearchURL) {
        return new FetchMovieReviews(movieSearchURL);
    }


    //Async inner class to fetch movie trailers on the internet
    private class FetchMovieTrailer extends LiveData<List<Trailer>>{

        private URL movieSearchURL;

        FetchMovieTrailer(URL movieSearchURL) {
            //this.context = context;
            this.movieSearchURL = movieSearchURL;
            loadMovieTrailer();
        }

        private void loadMovieTrailer(){
            new AsyncTask<URL,Void,List<Trailer>>(){

                String jsonData = null;
                //ArrayList<Trailer> trailers = new ArrayList<>();
                ArrayList<Trailer> trailers;
                @Override
                protected List<Trailer> doInBackground(URL... urls) {
                    URL searchUrl = urls[0];
                    try {
                        jsonData = NetworkUtils.fetchData(searchUrl);
                        trailers = JsonUtils.parseMovieTrailerJson(jsonData);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    return trailers;
                }

                @Override
                protected void onPostExecute(List<Trailer> trailerList) {
                    super.onPostExecute(trailerList);
                    setValue(trailerList);
                }
            }.execute(movieSearchURL);
        }
    }

    //Async inner class to fetch movie reviews on the internet
    private class FetchMovieReviews extends LiveData<List<MovieReview>>{

        private URL movieSearchURL;

        FetchMovieReviews(URL movieSearchURL) {
            //this.context = context;
            this.movieSearchURL = movieSearchURL;
            loadMovieReviews();

        }

        private void loadMovieReviews(){
            new AsyncTask<URL,Void,List<MovieReview>>(){

                String jsonData = null;
                //ArrayList<Trailer> trailers = new ArrayList<>();
                ArrayList<MovieReview> movieReviews;
                @Override
                protected List<MovieReview> doInBackground(URL... urls) {
                    URL searchUrl = urls[0];
                    try {
                        jsonData = NetworkUtils.fetchData(searchUrl);
                        movieReviews = JsonUtils.parseMovieReviewJson(jsonData);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    return movieReviews;
                }

                @Override
                protected void onPostExecute(List<MovieReview>  movieReviews) {
                    super.onPostExecute(movieReviews);
                    setValue(movieReviews);
                }
            }.execute(movieSearchURL);
        }
    }
}