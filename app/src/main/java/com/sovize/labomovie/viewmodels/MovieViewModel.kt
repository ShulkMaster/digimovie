package com.sovize.labomovie.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sovize.labomovie.database.RoomDB
import com.sovize.labomovie.database.entities.Movie
import com.sovize.labomovie.repositories.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel (val app : Application):  AndroidViewModel(app){

    private val repository : MovieRepository

    init {
        val movieDao = RoomDB.getDatabase(app).movieDao()
        repository = MovieRepository(movieDao)
    }


    private val scope = CoroutineScope(Dispatchers.IO)

    private val movieslist = MutableLiveData<MutableList<Movie>>()

    //private val movieResult = MutableLiveData<Movie>()


    fun fetchMovie(name: String) {
        viewModelScope.launch {

        }
        repository.movieSearch(name)
    }

    fun getMovieListVM(): LiveData<MutableList<Movie>> = movieslist

    fun fetchMovieByTitle(name: String) {


    }

    //fun getMovieResult(): LiveData<Movie> = movieResult


    fun insert(movie: Movie) = scope.launch {

    }


}
