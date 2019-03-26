package com.example.witne.popularmoviesstage2;

import com.example.witne.data.MovieDataRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class DetailMovieViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final MovieDataRepository movieDataRepository;

    public DetailMovieViewModelFactory(MovieDataRepository movieDataRepository) {
        this.movieDataRepository = movieDataRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new DetailMovieViewModel(movieDataRepository);
    }
}
