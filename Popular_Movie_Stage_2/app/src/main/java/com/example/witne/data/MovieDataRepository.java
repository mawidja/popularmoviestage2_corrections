package com.example.witne.data;

import android.app.Application;

import com.example.witne.popularmoviesstage2.MovieAppExecutor;

import java.util.List;

import androidx.lifecycle.LiveData;

public class MovieDataRepository {

    private MovieRoomDatabase roomDatabase;
    private MovieDao movieDao;
    private LiveData<List<Movie>> movieLiveData;
    private MovieAppExecutor movieAppExecutor;

    public MovieDataRepository(Application application) {
        roomDatabase = MovieRoomDatabase.getInstance(application.getApplicationContext());
        movieDao = roomDatabase.movieDao();
        movieAppExecutor = MovieAppExecutor.getInstance();
    }

    public LiveData<List<Movie>> getAllFavouriteMovies(){
        return movieLiveData = movieDao.getAllFavouriteMovies();
    }

    public void insertFavouriteMovie(final Movie movie)
    {
        movieAppExecutor.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                movieDao.insertFavouriteMovie(movie);
            }
        });
    }

    public void deleteFavouriteMovie(final int movieId){
        movieAppExecutor.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                movieDao.deleteFavouriteMovie(movieId);
            }
        });
    }

    public  LiveData<Movie> getFavouriteMovie(int movieId){
        return movieDao.getFavouriteMovie(movieId);
    }
}