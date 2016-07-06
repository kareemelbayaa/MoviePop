package com.example.harsha.moviepop;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Harsha on 7/6/2016.
 */

//Object that holds data about a movie
public class Movies implements Parcelable {

    String movieName;
    String releaseDate;
    int image; // drawable reference id (use R.drawable.imagename)

    //constructor that gives the object a name, date, and image
    public Movies(String name, String date, int image) {
        this.movieName = name;
        this.releaseDate = date;
        this.image = image;
    }

    //constructor that reads from a parcel to determine its values
    private Movies(Parcel in) {
        movieName = in.readString();
        releaseDate = in.readString();
        image = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return movieName + "---" + releaseDate + "---" + image;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(movieName);
        parcel.writeString(releaseDate);
        parcel.writeInt(image);
    }

    //Unwraps a parcel?
    public final Parcelable.Creator<Movies> CREATOR = new Parcelable.Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel parcel) {
            return new Movies(parcel);
        }
        @Override
        public Movies[] newArray(int i) {
            return new Movies[i];
        }
    };
}
