package com.example.witne.popularmoviesstage2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class FavouriteMovieViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final Application application;
    private final int mvoieId;

    public FavouriteMovieViewModelFactory(Application application, int mvoieId) {
        this.application = application;
        this.mvoieId = mvoieId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //return super.create(modelClass);
        return (T) new FavouriteMovieViewModel(application);
    }
}