package com.example.harsha.moviepop;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Harsha on 7/5/2016.
 */

//Grid of movie images with scrollview, each of which can be clicked on for further detail
public class MainActivityFragment extends Fragment {

    MoviesAdapter moviesAdapter;
    private ArrayList<Movies> moviesList;

    //dummy data to be modified later
    Movies[] mockData = {
            new Movies("The Gift", "2015", R.drawable.cupcake),
            new Movies("NightCrawler", "2014", R.drawable.eclair),
            new Movies("Bowfinger", "2003", R.drawable.icecream)
    };

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null || !savedInstanceState.containsKey("moviesKey")) {
            moviesList = new ArrayList<Movies>(Arrays.asList(mockData));
        } else {
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


    public class UpdateMovies extends AsyncTask<String, Void, String[]> {

        private final String LOG_TAG = UpdateMovies.class.getSimpleName();

        //TODO: Finish this method that takes a String of JSON data and gives us what we want
        /*
        private String[] getMovieDataFromJson(String moviesJsonStr, String sortType)
                throws JSONException {

            return niceMovieData;

        }
        */

        //Query data from MovieDB
        @Override
        protected String[] doInBackground(String... params) {

        /*
        // If there's no zip code, there's nothing to look up.  Verify size of params.
        if (params.length == 0) {
            return null;
        }
        */

            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;


            // Will contain the raw JSON response as a string.
            String moviesJsonStr = null;

            String format = "json";
            String sortType = getString(R.string.pref_sort_popular);

            try {
                // Construct the URL for the MovieDB query
                final String MOVIES_BASE_URL =
                        "http://api.themoviedb.org/3/movie/popular?api_key=" + getString(R.string.moviedb_api_key);
                Uri builtUri = Uri.parse(MOVIES_BASE_URL);
                URL url = new URL(builtUri.toString());

            /*
            final String QUERY_PARAM = "q";
            final String FORMAT_PARAM = "mode";
            final String UNITS_PARAM = "units";
            final String DAYS_PARAM = "cnt";
            final String APPID_PARAM = "APPID";


            Uri builtUri = Uri.parse(MOVIES_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, params[0])
                    .appendQueryParameter(FORMAT_PARAM, format)
                    .appendQueryParameter(UNITS_PARAM, units)
                    .appendQueryParameter(DAYS_PARAM, Integer.toString(numDays))
                    .appendQueryParameter(APPID_PARAM, BuildConfig.OPEN_WEATHER_MAP_API_KEY)
                    .build();

            URL url = new URL(builtUri.toString());
*/
                // Create the request to MovieDB, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                moviesJsonStr = buffer.toString();
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }


            /* Implement method that will edit the Json Data into what we want
            try {
                return getWeatherDataFromJson(forecastJsonStr, numDays);
            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }
            */

            // This will only happen if there was an error getting or parsing the forecast.
            return null;
        }

    }
}
