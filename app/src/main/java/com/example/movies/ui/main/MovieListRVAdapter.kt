package com.example.movies.ui.main

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.inflate
import com.example.movies.model.Movie
import com.squareup.picasso.Picasso

class MovieListRVAdapter: RecyclerView.Adapter<MovieListRVAdapter.MovieCardHolder>() {

    private  var movies:List<Movie> =  ArrayList()

    private  lateinit var onClick: OnMovieClick


    class MovieCardHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        var posetImage:ImageView;
           var title:TextView
         var year:TextView

        init {
            title=view.findViewById(R.id.title)
            year=view.findViewById(R.id.year)
            posetImage=view.findViewById(R.id.poster)

        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCardHolder {
        val inflatedView = parent.inflate(R.layout.movie_card_view, false)
        return MovieCardHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return  movies.size;
    }

    override fun onBindViewHolder(holder: MovieCardHolder, position: Int) {
        holder.title.text = movies[position].title;

        holder.year.text =  movies[position].year.toString();
        holder.itemView.setOnClickListener {
            onClick.onClick(movies[position].imdbID)
        }
        Picasso.get()
            .load(movies[position].poster)
            .resize(300,300)
            .into(holder.posetImage)
    }

    fun refreshData(moviesList:List<Movie>){
        movies=moviesList;
        notifyDataSetChanged();
    }


    fun setOnClickListener(onMovieClick:OnMovieClick){
        onClick=onMovieClick;
    }

    interface OnMovieClick{
        fun onClick(imdbID:String)
    }
}