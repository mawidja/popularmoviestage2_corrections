package com.example.witne.popularmoviesstage2;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import com.example.witne.data.MovieDataRepository;
import com.example.witne.data.MovieReview;
import com.example.witne.data.Trailer;
import com.example.witne.utilities.JsonUtils;
import com.example.witne.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class DetailMovieViewModel extends AndroidViewModel {

    //private final Context context;
    private MovieDataRepository movieDataRepository;

    public DetailMovieViewModel(Application application) {
        super(application);
        //this.context = application.getApplicationContext();
        movieDataRepository = new MovieDataRepository(application.getApplicationContext());
    }

    public LiveData<List<Trailer>> getMovieTrailers(URL movieSearchURL){
        return movieDataRepository.getMovieTrailers(movieSearchURL);
    }

    public LiveData<List<MovieReview>> getMovieReviews(URL movieSearchURL) {
        return movieDataRepository.getMovieReviews(movieSearchURL);
    }
}
