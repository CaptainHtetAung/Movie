package com.example.movies.ui.moviedetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.model.Movie
import com.example.movies.model.MovieDetail
import com.example.movies.model.Search
import com.example.movies.network.MovieService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieDetailViewModel : ViewModel() {
    var retrofit = Retrofit.Builder()
        .baseUrl("https://www.omdbapi.com")
        .addConverterFactory(GsonConverterFactory.create())

        .build()
    var service: MovieService = retrofit.create(MovieService::class.java)
    private val movieDetail: MutableLiveData<MovieDetail>? = MutableLiveData()


    fun findMovie(id: String?) {
        Log.w("id",id);
        var call = service.getMovieDetail(id)
        call.enqueue(object : Callback<MovieDetail> {
            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                Log.e("ERROR S", t.toString())
            }

            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                Log.w("REsponse",response.body()?.toString())
                movieDetail?.value=response.body()

            }

        })
    }
    fun getMovieDetail(): LiveData<MovieDetail>? {

        return movieDetail
    }
}

