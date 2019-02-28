package com.example.witne.popularmoviesstage2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.witne.data.Movie;
import com.example.witne.data.MovieDataRepository;
import com.example.witne.data.MovieReview;
import com.example.witne.data.Trailer;
import com.example.witne.utilities.JsonUtils;
import com.example.witne.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DetailMovieActivity extends AppCompatActivity implements MovieTrailerAdapter.ListItemClickListener,MovieReviewAdapter.ListItemClickListener {

    private MovieTrailerAdapter movieTrailerAdapter;
    private TextView tv_trailers;
    private List<Trailer> trailerList;
    URL movieSearchURL;

    //View Model for both Movie trailers and Movie Reviews....hope it works!
    DetailMovieViewModel detailMovieViewModel;

    private MovieReviewAdapter movieReviewAdapter;
    private TextView tv_movie_reviews;
    private ArrayList<MovieReview> movieReviews;

    private Intent intent;

    private MovieDataRepository movieDataRepository;
    private FavouriteMovieViewModel favouriteMovieModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        TextView tv_movie_title = findViewById(R.id.tv_movie_title);
        ImageView iv_movie_poster = findViewById(R.id.iv_movie_poster);
        TextView tv_movie_release_date = findViewById(R.id.tv_movie_release_date);
        TextView tv_movie_rating = findViewById(R.id.tv_movie_vote_average);
        TextView tv_movie_overview = findViewById(R.id.tv_movie_overview);
        final Switch bAddFavouriteMovie = findViewById(R.id.btAddFavouriteMovie);
        tv_trailers = findViewById(R.id.tv_trailers);
        tv_movie_reviews = findViewById(R.id.tv_movie_reviews);


        //set the movie trailer recycler view
        RecyclerView rv_movie_trailer = findViewById(R.id.rv_movie_trailer);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_movie_trailer.setLayoutManager(linearLayoutManager);
        rv_movie_trailer.setHasFixedSize(true);
        trailerList = new ArrayList<>();
        movieTrailerAdapter = new MovieTrailerAdapter(trailerList,this);
        rv_movie_trailer.setAdapter(movieTrailerAdapter);

        //set the movie reviews recycler view
        RecyclerView rv_movie_reviews = findViewById(R.id.rv_movie_reviews);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_movie_reviews.setLayoutManager(layoutManager);
        rv_movie_reviews.setHasFixedSize(true);
        movieReviews = new ArrayList<>();
        movieReviewAdapter = new MovieReviewAdapter(movieReviews,this);
        rv_movie_reviews.setAdapter(movieReviewAdapter);

        //get movie details from the intent that started the activity
        //Intent intentStartedThisActivity = getIntent();
        Bundle movieData = getIntent().getExtras();
        Movie movie = Objects.requireNonNull(movieData).getParcelable("Movie_Details");

        if(movie != null){
            tv_movie_title.setText(movie.getTitle());
            //String posterPath = IMAGE_BASE_URL + movie.getPoster_path();
            Picasso.get()
                    .load(movie.getPoster_path())
                    .into(iv_movie_poster);

            tv_movie_release_date.setText((movie.getRelease_date()));
            //tv_movie_rating.setText(movie.getVote_average());
            //tv_movie_rating.setText(String.valueOf(movie.getVote_average())+ " /10");
            String movieRating = getString(R.string.movie_rating_total,String.valueOf(movie.getVote_average()));
            tv_movie_rating.setText(movieRating);
            tv_movie_overview.setText(movie.getMovie_overview());
            String movieVideosOrReviews = "videos";
            String movieReviews = "reviews";

            if(isNetworkAvailable()) {
                startMovieTrailerSearch(movieVideosOrReviews,String.valueOf(movie.getMovieId()));
                startMovieReviewSearch(movieReviews,String.valueOf(movie.getMovieId()));
            }else{
                showErrorMessage();
            }

            bAddFavouriteMovie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        Toast.makeText(getApplicationContext(),"Favourite Movie",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"Un-Favourite",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private boolean isNetworkAvailable(){
        ConnectivityManager cm = (ConnectivityManager)getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return (networkInfo != null) && (networkInfo.isConnected());
    }

    private void setUpMovieTrailerMovieViewModel(){

        detailMovieViewModel = ViewModelProviders.of(this).get(DetailMovieViewModel.class);

        //create movie trailers observer
        detailMovieViewModel.getMovieTrailers(movieSearchURL).observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(List<Trailer> trailerList) {
                movieTrailerAdapter.setMovieTrailerAdapter(trailerList);
            }
        });
    }

    private void setUpMovieReviewMovieModel(){

        //create movie reviews observer
        detailMovieViewModel.getMovieReviews(movieSearchURL).observe(this, new Observer<List<MovieReview>>() {
             @Override
             public void onChanged(List<MovieReview> movieReviews) {
                    movieReviewAdapter.setMovieReviewAdapter(movieReviews);
                }
        });
    }

    private void showErrorMessage(){
        //tv_trailer_error_message.setVisibility(View.VISIBLE);
        tv_trailers.setText(getString(R.string.movie_trailer_error_message));
    }

    private void startMovieTrailerSearch(String movieVideosOrReviews, String movieIdOrKey ){
        movieSearchURL = NetworkUtils.buildUrl(movieVideosOrReviews,movieIdOrKey);
        setUpMovieTrailerMovieViewModel();
    }

    private void startMovieReviewSearch(String searchMovieReviews, String movieId){
        movieSearchURL = NetworkUtils.buildUrl(searchMovieReviews,movieId);
        setUpMovieReviewMovieModel();
    }

    private void showJSONData(String jsonData){
        //trailerList = new ArrayList<>();
        trailerList = JsonUtils.parseMovieTrailerJson(jsonData);
        movieTrailerAdapter.setMovieTrailerAdapter(trailerList);
    }

    private void displayTrailerOrReview(){
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }

    @Override
    public void onListItemClick(Trailer trailer){
        URL movieTrailerURL = NetworkUtils.buildUrl(null, trailer.getMovieKey());
        intent = new Intent(Intent.ACTION_VIEW,Uri.parse(movieTrailerURL.toString()));
        displayTrailerOrReview();
    }

    @Override
    public void onListItemClick(MovieReview movieReview) {
        intent = new Intent(Intent.ACTION_VIEW,Uri.parse(movieReview.getReviewUrl()));
        displayTrailerOrReview();
    }
}
