package com.example.witne.popularmoviesstage2;

import android.app.Application;

import com.example.witne.data.Movie;
import com.example.witne.data.MovieDataRepository;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class FavouriteMovieViewModel extends AndroidViewModel {

    private MovieDataRepository movieDataRepository;
    private LiveData<List<Movie>> movieList;
    private LiveData<Movie> movieLiveData;

    public FavouriteMovieViewModel(Application application) {
        super(application);
        movieDataRepository = new MovieDataRepository(application);
    }

    public LiveData<List<Movie>> getMovieLiveData(){
        return movieDataRepository.getAllFavouriteMovies();
    }

    public void insertFavouriteMovie(Movie movie){
        movieDataRepository.insertFavouriteMovie(movie);
    }

    public void deleteFavouriteMovie(int movieId){
        movieDataRepository.deleteFavouriteMovie(movieId);
    }

    public LiveData<Movie> getFavouriteMovie(int movieId){
        return movieDataRepository.getFavouriteMovie(movieId);
    }
}
