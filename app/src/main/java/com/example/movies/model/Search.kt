package com.example.movies.model

import com.google.gson.annotations.SerializedName

class Search {
    @SerializedName("Search")
    var result: List<Movie>? = null
}