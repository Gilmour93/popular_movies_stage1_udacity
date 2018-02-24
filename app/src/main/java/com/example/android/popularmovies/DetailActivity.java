package com.example.android.popularmovies;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.popularmovies.databinding.ActivityDetailBinding;
import com.example.android.popularmovies.models.Film;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActivityDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        binding.setFilm(getFilmInfo());
    }

    private Film getFilmInfo(){
        //TODO: retrieve informations from the previous activity
        return null;
    }
}
