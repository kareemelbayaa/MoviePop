package com.example.harsha.moviepop;

/**
 * Created by Harsha on 7/6/2016.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class MoviesAdapter extends ArrayAdapter<Movies> {
    private static final String LOG_TAG = MoviesAdapter.class.getSimpleName();

    /**
     * Custom constructor to inflate the layout file and populate with a List of data
     * @param context The current context. Used to inflate the layout file.
     * @param movies A List of Movies objects to display in a list
     */
    public MoviesAdapter(Activity context, List<Movies> movies) {
        //Initialize the ArrayAdapter for storage of the context and the list
        //0 is used for the second argument because it is mainly relevant for single textviews
        super(context, 0, movies);
}

    /**
     * Provides a view for an AdapterView
     *
     * @param position    The AdapterView position that is requesting a view
     * @param convertView The recycled view to populate.
     *
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Gets the Movies object from the ArrayAdapter at the appropriate position
        Movies movie = getItem(position);

        // Adapters recycle views to AdapterViews.
        // If this is a new View object we're getting, then inflate the layout.
        // If not, this view already has the layout inflated from a previous call to getView,
        // and we modify the View widgets as usual.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_movies, parent, false);
        }

        ImageView iconView = (ImageView) convertView.findViewById(R.id.list_item_icon);
        iconView.setImageResource(movie.image);

        TextView movieNameView = (TextView) convertView.findViewById(R.id.list_item_movies);
        movieNameView.setText(movie.movieName);

        TextView releaseDateView = (TextView) convertView.findViewById(R.id.list_item_movies_textview);
        releaseDateView.setText(movie.releaseDate);
        return convertView;
    }
}
