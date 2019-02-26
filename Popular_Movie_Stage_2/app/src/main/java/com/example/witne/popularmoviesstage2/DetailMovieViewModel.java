package com.example.witne.popularmoviesstage2;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

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

    private FetchMovieTrailer movieTrailer;
    //private LiveData<List<Trailer>> movieTrailerListLiveData;

    public DetailMovieViewModel(@NonNull Application application, URL movieSearchUrl) {
        super(application);
        movieTrailer = new FetchMovieTrailer(application,movieSearchUrl);
    }

    public LiveData<List<Trailer>> getMovieTrailers(){
        return movieTrailer;
    }

    //Async inner class to fetch network data
    public class FetchMovieTrailer extends LiveData<List<Trailer>>{
        private final Context context;
        private URL movieSearchURL;

        FetchMovieTrailer(Context context, URL movieSearchURL) {
            this.context = context;
            this.movieSearchURL = movieSearchURL;
            loadMovieTrailer();

        }

        private void loadMovieTrailer(){
            new AsyncTask<URL,Void,List<Trailer>>(){

                String jsonData = null;
                //ArrayList<Trailer> trailers = new ArrayList<>();
                ArrayList<Trailer> trailers;
                @Override
                protected List<Trailer> doInBackground(URL... urls) {
                    URL searchUrl = urls[0];
                    try {
                        jsonData = NetworkUtils.fetchData(searchUrl);
                        trailers = JsonUtils.parseMovieTrailerJson(jsonData);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    return trailers;
                }

                @Override
                protected void onPostExecute(List<Trailer> trailerList) {
                    super.onPostExecute(trailerList);
                    setValue(trailerList);
                }
            }.execute(movieSearchURL);
        }
    }
}
