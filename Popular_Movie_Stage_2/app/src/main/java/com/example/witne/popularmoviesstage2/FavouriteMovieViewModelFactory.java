package com.example.witne.popularmoviesstage2;

import com.example.witne.data.MovieDataRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class FavouriteMovieViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private MovieDataRepository movieDataRepository;
    public FavouriteMovieViewModelFactory(MovieDataRepository movieDataRepository) {
        this.movieDataRepository = movieDataRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //return super.create(modelClass);
        return (T) new FavouriteMovieViewModel(movieDataRepository);
    }
}
