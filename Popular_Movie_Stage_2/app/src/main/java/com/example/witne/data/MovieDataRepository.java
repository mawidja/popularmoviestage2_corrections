package com.example.witne.data;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;

public class MovieDataRepository {

    private MovieRoomDatabase roomDatabase;
    private MovieDao movieDao;
    private LiveData<List<Movie>> movieLiveData;

    public MovieDataRepository(Application application) {
        roomDatabase = MovieRoomDatabase.getInstance(application.getApplicationContext());
        movieDao = roomDatabase.movieDao();
    }

    public LiveData<List<Movie>> getAllFavouriteMovies(){
        return movieLiveData = movieDao.getAllFavouriteMovies();
    }

    public void insertFavouriteMovie(Movie movie){
        movieDao.insertFavouriteMovie(movie);
    }
}