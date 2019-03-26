package com.example.witne.popularmoviesstage2;

import android.app.Application;

import com.example.witne.data.FavouriteMovie;
import com.example.witne.data.MovieDataRepository;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class FavouriteMovieViewModel extends ViewModel {

    private MovieDataRepository movieDataRepository;
    //private LiveData<List<FavouriteMovie>> favouriteMovies;

    /*public FavouriteMovieViewModel(Application application) {
        super(application);
        this.movieDataRepository = MovieDataRepository.getInstance(this.getApplication());
    }*/

    public FavouriteMovieViewModel(MovieDataRepository movieDataRepository) {
        this.movieDataRepository = movieDataRepository;
    }

    public LiveData<List<FavouriteMovie>> getFavouriteMovies(){
        //return favouriteMovies;
        return movieDataRepository.getAllFavouriteMovies();
    }
}
