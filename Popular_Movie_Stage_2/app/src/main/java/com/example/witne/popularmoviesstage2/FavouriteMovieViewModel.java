package com.example.witne.popularmoviesstage2;

import android.app.Application;

import com.example.witne.data.FavouriteMovie;
import com.example.witne.data.MovieDataRepository;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class FavouriteMovieViewModel extends AndroidViewModel {

    private MovieDataRepository movieDataRepository;

    public FavouriteMovieViewModel(Application application) {
        super(application);
        movieDataRepository = MovieDataRepository.getInstance(this.getApplication());
    }

    public LiveData<List<FavouriteMovie>> getFavouriteMovies(){
        return movieDataRepository.getAllFavouriteMovies();
    }
}
