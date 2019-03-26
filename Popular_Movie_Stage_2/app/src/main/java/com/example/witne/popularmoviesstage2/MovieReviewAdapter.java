package com.example.witne.popularmoviesstage2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.witne.data.MovieReview;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieReviewAdapter extends RecyclerView.Adapter<MovieReviewAdapter.MovieReviewHolderAdapter> {

    private List<MovieReview> movieReviews;
    private MovieReview movieReview;
    private final ListItemClickListener listItemClickListener;

    public interface ListItemClickListener{
        void onListItemClick(MovieReview movieReview);
    }

    public MovieReviewAdapter(List<MovieReview> movieReviews, ListItemClickListener listItemClickListener) {
        this.movieReviews = movieReviews;
        this.listItemClickListener = listItemClickListener;
    }

    @NonNull
    @Override
    public MovieReviewHolderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutForListItem = R.layout.movie_review_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutForListItem,parent, false);
        return new MovieReviewHolderAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieReviewHolderAdapter holder, int position) {
        movieReview = movieReviews.get(position);
        holder.bind(movieReview);
    }

    @Override
    public int getItemCount() {
        return movieReviews.size();
    }

    public void setMovieReviewAdapter(List<MovieReview> movieReviews){
        this.movieReviews = movieReviews;
        notifyDataSetChanged();
    }

    public class MovieReviewHolderAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{

        Button bt_movie_review;

        public MovieReviewHolderAdapter(@NonNull View itemView) {
            super(itemView);
            bt_movie_review = itemView.findViewById(R.id.bt_movie_review);
            bt_movie_review.setOnClickListener(this);
        }

        // method for convenience to bind things up!
        public void bind(MovieReview movieReview){
            bt_movie_review.setText(movieReview.getMovieReviewAuthor());
            //bt_movie_trailer.setText(movieTrailer.getMovieName());
            //String movieKey = movieTrailer.getMovieKey();
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            movieReview = movieReviews.get(adapterPosition);
            listItemClickListener.onListItemClick(movieReview);
        }
    }
}
