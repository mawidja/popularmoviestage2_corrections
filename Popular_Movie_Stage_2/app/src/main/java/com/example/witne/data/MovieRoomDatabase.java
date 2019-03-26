package com.example.witne.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FavouriteMovie.class}, version = 1,exportSchema = false)
public abstract class MovieRoomDatabase extends RoomDatabase {

    private static final Object LOCK = new Object();
    private static MovieRoomDatabase sInstance;

    public static MovieRoomDatabase getInstance(Context context){
        if(sInstance == null){
            synchronized (LOCK){
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        MovieRoomDatabase.class,"favourite_movie_room_database")
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }return sInstance;
    }

    public abstract MovieDao movieDao();
}
