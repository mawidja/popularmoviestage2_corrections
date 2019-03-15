package com.example.witne.popularmoviesstage2;

import com.example.witne.data.MovieDataRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class DetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final MovieDataRepository movieDataRepository;
    public DetailViewModelFactory(MovieDataRepository movieDataRepository1) {
        movieDataRepository = movieDataRepository1;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //return super.create(modelClass);
        return (T)new DetailMovieViewModel(movieDataRepository);
    }
}
