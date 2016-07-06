package com.example.harsha.moviepop;

/**
 * Created by Harsha on 7/6/2016.
 */
public class Movies {

        String movieName;
        String releaseDate;
        int image; // drawable reference id

        public Movies(String name, String date, int image)
        {
            this.movieName = name;
            this.releaseDate = date;
            this.image = image;
        }
}
