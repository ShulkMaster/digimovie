package com.sovize.labomovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sovize.labomovie.database.entities.Movie
import com.sovize.labomovie.viewmodels.MovieViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var vm: MovieViewModel
    private val observer = Observer<List<Movie>>{
        it.forEach { movie ->
            Log.d("MainAcitivty", movie.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        vm.fetchMovie("romance")
        vm.movieList.observe(this, observer)
    }
}
