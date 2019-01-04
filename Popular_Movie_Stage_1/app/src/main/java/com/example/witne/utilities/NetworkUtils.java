 package com.example.witne.utilities;

//import android.graphics.Movie;
import com.example.witne.data.Movie;
import com.example.witne.utilities.JsonUtils;

import android.net.Uri;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    //popularMoviesUrl = "https://api.themoviedb.org/3/movie/top_rated?api_key=98626ee46e48c23fface6dac4d55c5ea&language=en-US&page=1";
    //topRatedMoviesUrl ="https://api.themoviedb.org/3/movie/top_rated?api_key=98626ee46e48c23fface6dac4d55c5ea&language=en-US&page=1"

    private static final String MOVIE_BASE_URL = "https://api.themoviedb.org/3/movie/";
    private static final String API_KEY = "api_key";
    private static final String myPersonalApiKey = "98626ee46e48c23fface6dac4d55c5ea";

    private static final String PARAM_LANGUAGE = "language";
    private static final String language = "en-US";

    private static final String PARAM_PAGE = "page";
    private static final String page = "1";


    //public static final String popularMoviesUrl = "https://api.themoviedb.org/3/movie/popular?api_key=\"+myPersonalApiKey+\"&language=en-US";
    //public static final String popularMoviesUrl = "https://api.themoviedb.org/3/movie/popular?api_key=\"" + myPersonalApiKey;

    public static URL buildUrl(String popularOrTopRatedMovie) {
        Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                .appendPath(popularOrTopRatedMovie)
                .appendQueryParameter(API_KEY, myPersonalApiKey)
                .appendQueryParameter(PARAM_LANGUAGE,language)
                .appendQueryParameter(PARAM_PAGE,page)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
            Log.d(TAG,  url.toString());
        } catch (MalformedURLException e) {
            Log.d(TAG,"Error in building the URL.....Ooooouch!");
            e.printStackTrace();
        }

        return url;
    }

    public static String fetchData(URL url)throws  IOException{

        //Open HTTP connection
        //URL newUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        try{
            //Reading from open connection object
            InputStream inputStream = connection.getInputStream();

            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");

            if(scanner.hasNext()){
                return  scanner.next();
            }else{
                return null;
            }
        }finally {
            connection.disconnect();
        }
    }
}
