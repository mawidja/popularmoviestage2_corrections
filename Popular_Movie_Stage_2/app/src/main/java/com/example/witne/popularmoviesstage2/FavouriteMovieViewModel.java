package com.example.witne.popularmoviesstage2;

import android.app.Application;
import android.content.Context;

import com.example.witne.data.Movie;
import com.example.witne.data.MovieDataRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class FavouriteMovieViewModel extends AndroidViewModel {

    private MovieDataRepository movieDataRepository;
    //private final Context context;
    //private LiveData<List<Movie>> movieList;
    ///private LiveData<Movie> movieLiveData;

    public FavouriteMovieViewModel(@NonNull Application application) {
        super(application);
        //this.context = application.getApplicationContext();
        movieDataRepository = new MovieDataRepository(application);
    }

    public LiveData<List<Movie>> getFavouriteMovies(){
        return movieDataRepository.getAllFavouriteMovies();
    }

    public void insertFavouriteMovie(Movie movie){
        movieDataRepository.insertFavouriteMovie(movie);
    }

    public void deleteFavouriteMovie(Movie movie){
        movieDataRepository.deleteFavouriteMovie(movie);
    }

    public LiveData<Movie> getFavouriteMovie(int movieId){
        return movieDataRepository.getFavouriteMovie(movieId);
    }
}
