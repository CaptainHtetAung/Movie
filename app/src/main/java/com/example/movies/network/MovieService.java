package com.example.movies.network;


import com.example.movies.model.Movie;
import com.example.movies.model.MovieDetail;
import com.example.movies.model.Search;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {
  @GET("/?apikey=b9bd48a6&type=movie")
  Call<Search> findMovies(@Query("s") String title);
  @GET("/?apikey=b9bd48a6")
  Call<MovieDetail> getMovieDetail(@Query("i") String id);

}