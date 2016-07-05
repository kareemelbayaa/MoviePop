package com.example.harsha.moviepop;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Harsha on 7/5/2016.
 */

//Grid of movie images with scrollview, each of which can be clicked on for further detail
public class MoviesFragment extends Fragment {
    ArrayAdapter<String> movieData;
    public MoviesFragment() {
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

        //dummy data to be modified later
        String[] mockData = {
                "Star Wars", "Monty Python", "The Gift",
                "Bowfinger", "NightCrawler", "Donnie Darko"
        };
        List<String> mockDataList = new ArrayList<String>(Arrays.asList(mockData));

        ArrayAdapter<String> movieData = new ArrayAdapter<String>(
                getActivity(),
                R.layout.fragment_movies,
                R.id.container,
                mockDataList
        );

        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.fragment_movies);
        listView.setAdapter(movieData);

        return rootView;

    }

}
