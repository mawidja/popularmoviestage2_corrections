package com.example.witne.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Movie.class}, version = 1,exportSchema = false)
public abstract class MovieRoomDatabase extends RoomDatabase {

    private static final Object LOCK = new Object();
    private static MovieRoomDatabase sInstance;

    public static MovieRoomDatabase getInstance(final Context context){
        if(sInstance == null){
            synchronized (LOCK){
                sInstance = Room.databaseBuilder(context.getApplicationContext(),MovieRoomDatabase.class,"movie_room_database")
                        .build();
            }
        }return sInstance;
    }

    public abstract MovieDao movieDao();
}
