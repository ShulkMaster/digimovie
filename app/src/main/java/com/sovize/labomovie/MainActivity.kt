package com.sovize.labomovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sovize.labomovie.adapters.MovieAdapter
import android.util.Log
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.sovize.labomovie.database.entities.Movie
import com.sovize.labomovie.utils.AppLogger
import com.sovize.labomovie.viewmodels.MovieViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var vm: MovieViewModel
    private lateinit var rv: RecyclerView
    private val observer = Observer<List<Movie>> {
        updateRecycler(it)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        rv = findViewById(R.id.RecyclerView)
        vm.movieList.observe(this, observer)
        vm.loadCache()
        setEditableRT()
    }

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

}
