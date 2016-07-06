package com.example.harsha.moviepop;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Harsha on 7/5/2016.
 */

//Grid of movie images with scrollview, each of which can be clicked on for further detail
public class MainActivityFragment extends Fragment {

    MoviesAdapter moviesAdapter;

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
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.moviesfragment, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        /*List<String> mockDataList = new ArrayList<String>(Arrays.asList(mockData));

        movieData = new ArrayAdapter<String>(
                getActivity(),
                R.layout.list_item_movies,
                R.id.list_item_movies_textview,
                mockDataList
        );
        */

        moviesAdapter = new MoviesAdapter(getActivity(), Arrays.asList(mockData));
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        GridView gridView = (GridView) rootView.findViewById(R.id.fragment_movies);
        gridView.setAdapter(moviesAdapter);

        return rootView;

    }

}
