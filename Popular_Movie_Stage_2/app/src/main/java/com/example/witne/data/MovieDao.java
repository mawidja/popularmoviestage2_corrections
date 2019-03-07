package com.example.witne.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


@Dao
public interface MovieDao {

    @Query("select * from movie")
    LiveData<List<Movie>> getAllFavouriteMovies();

    @Insert
    void insertFavouriteMovie(Movie movie);

    @Delete
    void deleteFavouriteMovie(Movie movie);

    @Query("select * from movie where movieid = :movieId")
    LiveData<Movie> getFavouriteMovie(int movieId);

}
