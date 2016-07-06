package com.example.harsha.moviepop;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Harsha on 7/5/2016.
 */

//Grid of movie images with scrollview, each of which can be clicked on for further detail
public class MainActivityFragment extends Fragment {

    MoviesAdapter moviesAdapter;
    private ArrayList<Movies> moviesList;

    //dummy data to be modified later
    Movies[] mockData = {
            new Movies("The Gift","2015",R.drawable.cupcake),
            new Movies("NightCrawler","2014",R.drawable.eclair),
            new Movies("Bowfinger","2003",R.drawable.icecream)
    };

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null || !savedInstanceState.containsKey("moviesKey")) {
            moviesList = new ArrayList<Movies>(Arrays.asList(mockData));
    }
        else {
            moviesList = savedInstanceState.getParcelableArrayList("moviesKey");
        }
        setHasOptionsMenu(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("moviesKey", moviesList);
        super.onSaveInstanceState(outState);
    }

    //Use a MoviesAdapter to display the data onto fragment_movies layout
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        moviesAdapter = new MoviesAdapter(getActivity(), moviesList);
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        GridView gridView = (GridView) rootView.findViewById(R.id.fragment_movies);
        gridView.setAdapter(moviesAdapter);

        //Determines what happens when a movie is selected.
        //TODO: Add intent that leads to a detail activity screen
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movies moviesClick = moviesAdapter.getItem(i);
                moviesClick.movieName += ":)";
                moviesAdapter.notifyDataSetChanged();
            }
        });


        return rootView;

    }

}
