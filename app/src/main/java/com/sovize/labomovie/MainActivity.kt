package com.sovize.labomovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< HEAD
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sovize.labomovie.adapters.MovieAdapter
=======
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sovize.labomovie.database.entities.Movie
>>>>>>> 9fcc7dfa894b4791da3e39e90ff3d8891030a5ec
import com.sovize.labomovie.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var vm: MovieViewModel
<<<<<<< HEAD
    private lateinit var adapterM : MovieAdapter
=======
    private val observer = Observer<List<Movie>>{
        it.forEach { movie ->
            Log.d("MainAcitivty", movie.toString())
        }
    }
>>>>>>> 9fcc7dfa894b4791da3e39e90ff3d8891030a5ec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm = ViewModelProviders.of(this).get(MovieViewModel::class.java)
<<<<<<< HEAD
        vm.fetchMovie("clannad")

        //bind()
=======
        vm.fetchMovie("romance")
        vm.movieList.observe(this, observer)
>>>>>>> 9fcc7dfa894b4791da3e39e90ff3d8891030a5ec
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
