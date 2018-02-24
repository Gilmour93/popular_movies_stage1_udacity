package com.example.android.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.popularmovies.utils.NetworkUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";

    private RecyclerView mFilmsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFilmsList = (RecyclerView) findViewById(R.id.rv_films);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // set Horizontal Orientation
        mFilmsList.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView

        populateUI(MainActivity.this);
    }

    //TODO: Implement this method
    private void populateUI(Context context){
        if (!isOnline()) {
            String message = "Cannot Contact Host. \nCheck your internet connection.";
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            return;
        }
        //TODO: populate
        makeGithubMainQuery(getSortByPreferences());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings){
            Intent intent = new Intent (MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    public class GithubQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL githubSearchUrl = urls[0];
            String githubSearchResults = null;
            try {
                githubSearchResults = NetworkUtils.getResponseFromHttpUrl(githubSearchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return githubSearchResults;
        }

        @Override
        protected void onPostExecute(String s) {
            if (s != null && !s.equals("")){
                //TODO: save result
            }
        }
    }

    private void makeGithubMainQuery(String sortby) {
        URL githubSearchUrl = NetworkUtils.discoverUrl(sortby);

        new GithubQueryTask().execute(githubSearchUrl);

    }

    private String getSortByPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String sortby = sharedPreferences.getString(getString(R.string.pref_sort_key),
                getResources().getString(R.string.pref_sort_mostpopular_value));
        return sortby;
    }

}
