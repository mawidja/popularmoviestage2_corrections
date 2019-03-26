package com.example.witne.popularmoviesstage2;

import com.example.witne.data.FavouriteMovie;
import com.example.witne.data.MovieDataRepository;
import com.example.witne.data.MovieReview;
import com.example.witne.data.Trailer;

import java.net.URL;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class DetailMovieViewModel extends ViewModel {

    private MovieDataRepository movieDataRepository;

    public DetailMovieViewModel(MovieDataRepository movieDataRepository) {
        this.movieDataRepository = movieDataRepository;
    }

    public LiveData<FavouriteMovie> getFavouriteMovie(int movieId){
        return movieDataRepository.getFavouriteMovie(movieId);
    }

    public LiveData<List<Trailer>> getMovieTrailers(URL movieSearchURL){
        return movieDataRepository.getMovieTrailers(movieSearchURL);
    }

    public LiveData<List<MovieReview>> getMovieReviews(URL movieSearchURL) {
        return movieDataRepository.getMovieReviews(movieSearchURL);
    }

    public void insertFavouriteMovie(FavouriteMovie favouriteMovie){
        movieDataRepository.insertFavouriteMovie(favouriteMovie);
    }

    public void deleteFavouriteMovie(FavouriteMovie favouriteMovie){
        movieDataRepository.deleteFavouriteMovie(favouriteMovie);
    }
}
