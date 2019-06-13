package com.sovize.labomovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< HEAD
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sovize.labomovie.adapters.MovieAdapter
import android.util.Log
=======
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sovize.labomovie.adapters.MovieAdapter
>>>>>>> 6a630f0162f47e2082c0005ec348b4cd56fb09b3
import com.sovize.labomovie.database.entities.Movie
import com.sovize.labomovie.utils.AppLogger
import com.sovize.labomovie.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var vm: MovieViewModel
<<<<<<< HEAD
    //private lateinit var adapterM : MovieAdapter
    private val observer = Observer<List<Movie>>{
=======
    private lateinit var rv: RecyclerView
    private val observer = Observer<List<Movie>> {
>>>>>>> 6a630f0162f47e2082c0005ec348b4cd56fb09b3
        it.forEach { movie ->
            Log.d("MainAcitivty", movie.toString())
        }
        updateRecycler(it)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm = ViewModelProviders.of(this).get(MovieViewModel::class.java)
<<<<<<< HEAD
        vm.fetchMovie("clannad")

        //bind()
        vm.fetchMovie("romance")
=======
        rv = findViewById(R.id.RecyclerView)
>>>>>>> 6a630f0162f47e2082c0005ec348b4cd56fb09b3
        vm.movieList.observe(this, observer)
        setEditableRT()
    }

<<<<<<< HEAD
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
=======
    private fun setEditableRT() {
        findViewById<EditText>(R.id.idBuscar).addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                Log.d(AppLogger.main, s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d(AppLogger.main, s.toString())
                vm.fetchMovie(s.toString())
            }
        })

    }

    private fun updateRecycler(list: List<Movie>) {
        if (rv.adapter != null) {
            rv.apply {
                setHasFixedSize(true)
                adapter = MovieAdapter(list) {
                    Log.d(AppLogger.main, "Toco $it")
                }
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        } else {
            rv.swapAdapter(MovieAdapter(list) {
                Log.d(AppLogger.main, "Toco $it")
            }, true)
        }

    }

>>>>>>> 6a630f0162f47e2082c0005ec348b4cd56fb09b3
}
