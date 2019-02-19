package com.example.witne.popularmoviesstage2;

import android.app.Application;

import com.example.witne.data.Movie;
import com.example.witne.data.MovieDataRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MainMovieViewModel extends AndroidViewModel {

    private MovieDataRepository movieDataRepository;
    private LiveData<List<Movie>> movieLiveData;

    public MainMovieViewModel(Application application) {
        super(application);

        movieDataRepository = new MovieDataRepository(application);
        movieLiveData = movieDataRepository.getAllFavouriteMovies();
    }

    public LiveData<List<Movie>> getMovieLiveData(){
        return movieLiveData;
    }
}
