package com.example.witne.popularmoviesstage2;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.LogRecord;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;

public class MovieAppExecutor {

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static MovieAppExecutor sInstance;
    private final Executor diskIO;
    private final Executor mainThread;
    private final Executor networkIO;

    private MovieAppExecutor(Executor diskIO, Executor networkIO, Executor mainThread) {
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainThread = mainThread;
    }

    public static MovieAppExecutor getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new MovieAppExecutor(Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(3),
                        new MainThreadExecutor());
            }
        }
        return sInstance;
    }

    public Executor diskIO() {
        return diskIO;
    }

    public Executor mainThread() {
        return mainThread;
    }

    public Executor networkIO() {
        return networkIO;
    }

    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }

}
