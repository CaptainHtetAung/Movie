package com.example.movies.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movies.MovieDetailActivity
import com.example.movies.R
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment() {


    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var adapter: MovieListRVAdapter
    private lateinit var linearLayoutManager: GridLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.main_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearLayoutManager = GridLayoutManager(context,2)
        recyclerView.layoutManager = linearLayoutManager
        adapter = MovieListRVAdapter()
        recyclerView.adapter = adapter

        adapter.setOnClickListener(object : MovieListRVAdapter.OnMovieClick{
            override fun onClick(imdbID: String) {
                var intent=Intent(context,MovieDetailActivity::class.java);
                intent.putExtra("movie_id",imdbID);
                startActivity(intent)
            }

        })


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.findMovie("marvel")

        viewModel.getMovies()?.observe(this, Observer {movies->
            adapter.refreshData(movies);
        });


    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu,menu)
        val searchViewItem = menu!!.findItem(R.id.app_bar_search)
        val searchView: SearchView = MenuItemCompat.getActionView(searchViewItem) as SearchView
        searchView.queryHint="Enter movie title here"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                /*   if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(MainActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }*/
                if(query!=null){
                    viewModel.findMovie(query)

                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }
}
