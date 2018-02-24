package com.example.android.popularmovies.models;

public class Film {
    private String title;
    private String releaseDate;
    private String moviePoster;
    private String voteAverage;
    private String overview;
    private String duration;

    public Film (){}

    public Film (String title, String releaseDate, String moviePoster, String voteAverage,
                 String overview, String duration){
        this.title = title;
        this.releaseDate = releaseDate;
        this.moviePoster = moviePoster;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.duration = duration;
    }

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getReleaseDate() {return releaseDate;}

    public void setReleaseDate(String releaseDate) {this.releaseDate = releaseDate;}

    public String getMoviePoster() {return moviePoster;}

    public void setMoviePoster(String moviePoster) {this.moviePoster = moviePoster;}

    public String getVoteAverage() {return voteAverage;}

    public void setVoteAverage(String voteAverage) {this.voteAverage = voteAverage;}

    public String getOverview() {return overview;}

    public void setOverview(String overview) {this.overview = overview;}

    public String getDuration() {return duration;}

    public void setDuration(String duration) {this.duration = duration;}

}
