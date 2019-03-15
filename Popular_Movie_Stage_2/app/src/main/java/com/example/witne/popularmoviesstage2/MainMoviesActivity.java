package com.example.witne.popularmoviesstage2;

import com.example.witne.data.Movie;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
//import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.witne.data.MovieDataRepository;
import com.example.witne.data.Trailer;
import com.example.witne.popularmoviesstage2.MovieAdapter;
import com.example.witne.popularmoviesstage2.R;
import com.example.witne.utilities.JsonUtils;
import com.example.witne.utilities.NetworkUtils;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainMoviesActivity extends AppCompatActivity implements MovieAdapter.ListItemClickLister,
        LoaderManager.LoaderCallbacks<String>{

    //to uniquely identify the loader
    private static final int MOVIE_SEARCH_LOADER = 46;
    private LoaderManager loaderManager;
    //private String movieSearchQuery;
    private static final String MOVIE_SEARCH_KEY = "movieSearchKey";

    private static final String MOVIE_SEARCH_QUERY = "queryMovies";
    //private static final String DEFAULT_MOVIE_SEARCH = "popular";
    private String popularOrTopRatedMovies;
    //private ArrayList<Movie> movieList;
    private List<Movie> movieList;
    private MovieAdapter movieAdapter;

    private LiveData<List<Movie>> movieList1;
    /*@BindView(R.id.rv_popularMovies)
    RecyclerView recyclerView;

    @BindView(R.id.pb_loading_indicator)
    ProgressBar progressBar;*/

    private TextView tv_error_message;
    private ProgressBar progressBar;

    //private MovieDataRepository movieDataRepository;
    //private FavouriteMovieViewModel favouriteMovieViewModel;

    /*@Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        popularOrTopRatedMovies = savedInstanceState.getString(MOVIE_SEARCH_KEY);
        super.onRestoreInstanceState(savedInstanceState);
    }*/

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(MOVIE_SEARCH_KEY,popularOrTopRatedMovies);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get the movie search string query
        if(savedInstanceState != null){
            popularOrTopRatedMovies = savedInstanceState.getString(MOVIE_SEARCH_KEY);
        }else{
            //build the url string - default to 'popular movies'
            popularOrTopRatedMovies = "popular";
        }
        //popularOrTopRatedMovies = savedInstanceState.getString(MOVIE_SEARCH_KEY);
        setContentView(R.layout.activity_main_movies);
        //ButterKnife.bind(this);

        tv_error_message = findViewById(R.id.tv_error_message);
        RecyclerView recyclerView = findViewById(R.id.rv_popularMovies);
        progressBar = findViewById(R.id.pb_loading_indicator);

        //define layout manager for the recycler view
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager1);

        //to improve performance of the recycler view
        recyclerView.setHasFixedSize(true);

        //initialize adapter and set to the recycler view object
        movieList = new ArrayList<>();
        movieAdapter = new MovieAdapter(movieList,this);
        recyclerView.setAdapter(movieAdapter);

        //check for network connection
        if(isNetworkAvailable()) {
            startMovieSearch(popularOrTopRatedMovies);
        }else{
            showErrorMessage();
        }

    }

    private boolean isNetworkAvailable(){
        ConnectivityManager cm = (ConnectivityManager)getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return (networkInfo != null) && (networkInfo.isConnected());
    }

    private void showErrorMessage(){
        progressBar.setVisibility(View.INVISIBLE);
        tv_error_message.setVisibility(View.VISIBLE);
    }

    private void showJSONData(String jsonData){
        progressBar.setVisibility(View.INVISIBLE);
        tv_error_message.setVisibility(View.INVISIBLE);
        movieList = JsonUtils.parseMovieJson(jsonData);
        movieAdapter.setMovieAdapter(movieList);
    }

    private void startMovieSearch(String popularOrTopRatedMovies){
        URL movieSearchURL = NetworkUtils.buildUrl(popularOrTopRatedMovies,null);
        //fetch data on separate thread
        // and initialize the recycler viewer with data from movie adapter
        //New  FetchMovieTask().execute(movieSearchURL);

        Bundle queryBundle = new Bundle();
        queryBundle.putString(MOVIE_SEARCH_QUERY,movieSearchURL.toString());

        loaderManager = LoaderManager.getInstance(this);
        Loader<String> fetchMovieLoader = loaderManager.getLoader(MOVIE_SEARCH_LOADER);
        if(fetchMovieLoader == null ){
            loaderManager.initLoader(MOVIE_SEARCH_LOADER,queryBundle,this);
        }else {
            loaderManager.restartLoader(MOVIE_SEARCH_LOADER,queryBundle,this);
        }
    }
    @Override
    public void onListItemClick(Movie movie) {
        //Toast.makeText(this.getBaseContext(),"List item clicked!",Toast.LENGTH_LONG).show();
        Intent startDetailMovieIntent = new Intent(this, DetailMovieActivity.class);
        startDetailMovieIntent.putExtra("Movie_Details",movie);
        startActivity(startDetailMovieIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //inflate the menu
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int itemThatWasSelected = menuItem.getItemId();
        if(itemThatWasSelected == R.id.action_popular_movies){
            popularOrTopRatedMovies = "popular";
            startMovieSearch(popularOrTopRatedMovies);
            return true;
        }
        if(itemThatWasSelected == R.id.action_top_rated_movies){
            popularOrTopRatedMovies = "top_rated";
            startMovieSearch(popularOrTopRatedMovies);
            return true;
        }
        if(itemThatWasSelected == R.id.action_favourite_movies){
            FavouriteMovieViewModel favouriteMovieViewModel = ViewModelProviders.of(this).get(FavouriteMovieViewModel.class);
            favouriteMovieViewModel.getFavouriteMovies().observe(this, new Observer<List<Movie>>() {
                @Override
                public void onChanged(List<Movie> movies) {
                    loadFavouriteMovies(movieList);
                }
            });
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void loadFavouriteMovies(List<Movie> movieList1){
        progressBar.setVisibility(View.INVISIBLE);
        tv_error_message.setVisibility(View.INVISIBLE);
        movieAdapter.setMovieAdapter(movieList1);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<String>(this) {
            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                if(args == null){
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                forceLoad();
            }

            @Nullable
            @Override
            public String loadInBackground() {
                String jsonData;
                String searchMovieQuery = args.getString(MOVIE_SEARCH_QUERY);
                if(searchMovieQuery == null || TextUtils.isEmpty(searchMovieQuery)){
                    return null;
                }try {
                    URL movieSearchURL = new URL(searchMovieQuery);
                    jsonData = NetworkUtils.fetchData(movieSearchURL);
                    return jsonData;
                }catch (IOException e){
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        progressBar.setVisibility(View.INVISIBLE);
        if(data != null && !data.equals("")) {
            showJSONData(data);
        }else{
            showErrorMessage();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
