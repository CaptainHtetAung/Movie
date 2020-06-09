package com.example.movies.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.model.Movie
import com.example.movies.model.Search
import com.example.movies.network.MovieService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainViewModel : ViewModel() {
    var retrofit = Retrofit.Builder()
        .baseUrl("https://www.omdbapi.com")
        .addConverterFactory(GsonConverterFactory.create())

        .build()
    var service: MovieService = retrofit.create(MovieService::class.java)
    private val movieList: MutableLiveData<List<Movie>>? = MutableLiveData()


    fun findMovie(title: String) {
        var call = service.findMovies(title)
        call.enqueue(object : Callback<Search> {
            override fun onFailure(call: Call<Search>, t: Throwable) {
                Log.e("ERROR S", t.toString())
            }

            override fun onResponse(call: Call<Search>, response: Response<Search>) {
                movieList?.value=response.body()?.result
                Log.w("REsponse",response.body()?.result.toString())


            }

        })
    }
    fun getMovies(): LiveData<List<Movie>>? {
        if (movieList?.value == null) {
            movieList?.value=ArrayList()
        }
        return movieList
    }
}



