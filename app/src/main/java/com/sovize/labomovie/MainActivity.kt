package com.sovize.labomovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sovize.labomovie.adapters.MovieAdapter
import android.util.Log
import com.sovize.labomovie.database.entities.Movie
import com.sovize.labomovie.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var vm: MovieViewModel
    //private lateinit var adapterM : MovieAdapter
    private val observer = Observer<List<Movie>>{
        it.forEach { movie ->
            Log.d("MainAcitivty", movie.toString())
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        vm.fetchMovie("clannad")

        //bind()
        vm.fetchMovie("romance")
        vm.movieList.observe(this, observer)
    }

    /*fun bind(){
        vm = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        val linearLayoutManager = LinearLayoutManager(this)
        vm.getMovieListVM().observe(this, Observer { result ->
            adapterM.changeDataSet(result)
        })

        RecyclerView.apply {
            adapter=adapterM
        }
    }*/
}
