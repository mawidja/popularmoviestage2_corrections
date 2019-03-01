package com.example.witne.popularmoviesstage2;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.LogRecord;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;

public class MovieAppExecutor {

    private static final Object LOCK = new Object();
    private static MovieAppExecutor sInstance;
    private final Executor diskIO;
    private final Executor mainThread;
    private final Executor networkIO;

    public Executor getDiskIO() {
        return diskIO;
    }

    public Executor getMainThread() {
        return mainThread;
    }

    public Executor getNetworkIO() {
        return networkIO;
    }

    private MovieAppExecutor(Executor diskIO, Executor mainThread, Executor networkIO){
        this.diskIO = diskIO;
        this.mainThread = mainThread;
        this.networkIO = networkIO;
    }

    public static MovieAppExecutor getInstance(){
        if(sInstance == null){
            synchronized (LOCK) {
                sInstance = new MovieAppExecutor(Executors.newSingleThreadExecutor(),Executors.newFixedThreadPool(3),
                        new MainThreadExecutor());
            }
        }
        return sInstance;
    }

    private static class MainThreadExecutor implements Executor{
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
