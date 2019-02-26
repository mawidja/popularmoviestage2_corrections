package com.example.witne.popularmoviesstage2;

import android.app.Application;

import java.net.URL;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class DetailMovieViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Application application;
    private URL movieUrl;

    public DetailMovieViewModelFactory(Application application, URL movieUrl) {

        this.application = application;
        this.movieUrl = movieUrl;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //return super.create(modelClass);
        return (T) new DetailMovieViewModel(application,movieUrl);
    }
}
