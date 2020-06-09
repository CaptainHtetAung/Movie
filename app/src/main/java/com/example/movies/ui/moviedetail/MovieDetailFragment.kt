package com.example.movies.ui.moviedetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.movies.R
import com.example.movies.databinding.MovieDetailFragmentBinding


private const val MOVIE_ID = "movie_id"
class MovieDetailFragment : Fragment() {

    companion object {
        fun newInstance(movieId: String) =
            MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(MOVIE_ID, movieId)
                }
            }
    }


    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var binding:MovieDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         binding =DataBindingUtil.inflate(
            inflater, R.layout.movie_detail_fragment, container, false);
        //here data must be an instance of the class MarsDataProvider
        return  binding.getRoot();
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)
       var movieID =   arguments?.getString(MOVIE_ID)

        Log.w("Movie ID",movieID)
        viewModel.findMovie(movieID)

        viewModel.getMovieDetail()?.observe(this, Observer{movieDetail->
            binding.movieDetail=movieDetail
        });

    }

}
