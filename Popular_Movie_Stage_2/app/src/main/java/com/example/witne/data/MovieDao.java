package com.example.witne.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


@Dao
public interface MovieDao {

    @Query("select * from movie")
    LiveData<List<Movie>> getAllFavouriteMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavouriteMovie(Movie movie);

    @Query("delete from movie where movieid = :movieId")
    void deleteFavouriteMovie(int movieId);

    @Query("select * from movie where movieid = :movieId")
    LiveData<Movie> getFavouriteMovie(int movieId);

}
