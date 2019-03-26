package com.example.witne.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


@Dao
public interface MovieDao {

    @Query("select * from favourite_movie")
    LiveData<List<FavouriteMovie>> getAllFavouriteMovies();

    @Insert
    void insertFavouriteMovie(FavouriteMovie favouriteMovie);

    @Delete
    void deleteFavouriteMovie(FavouriteMovie favouriteMovie);

    @Query("select * from favourite_movie where movieid = :movieId")
    LiveData<FavouriteMovie> getFavouriteMovie(int movieId);

}
