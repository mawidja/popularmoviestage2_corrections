package com.example.witne.popularmoviesstage2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class DetailMovieViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Context context;

    public DetailMovieViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new DetailMovieViewModel(context);
    }
}
