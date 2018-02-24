package com.example.android.popularmovies.utils;

import android.net.Uri;

import com.example.android.popularmovies.BuildConfig;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    final static String IMAGE_URL =
            "http://image.tmdb.org/t/p/";

    final static String IMAGE_SECURE_URL =
            "https://image.tmdb.org/t/p/";

    final static String BASE_URL =
            "http://api.themoviedb.org/3/discover/movie";

    private static final String SORT_BY_POPULAR ="popular";
    private static final String SORT_BY_TOPRATED ="top_rated";

    final static String QUERY_PARAM = "?";
    private static final String API_KEY = BuildConfig.API_KEY;

    public static URL imageUrl (String imageUrl, String sortby) {
        Uri builtUri = Uri.parse(imageUrl).buildUpon()
                .appendPath(sortby)
                .appendQueryParameter(QUERY_PARAM , API_KEY)
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static URL discoverUrl (String sortby){
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendPath(sortby)
                .appendQueryParameter(QUERY_PARAM, API_KEY)
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
