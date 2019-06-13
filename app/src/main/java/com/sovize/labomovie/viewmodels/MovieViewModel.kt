package com.sovize.labomovie.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sovize.labomovie.database.RoomDB
import com.sovize.labomovie.database.entities.Movie
import com.sovize.labomovie.repositories.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel (app : Application):  AndroidViewModel(app){

    private val movieDao = RoomDB.getDatabase(app).movieDao()
    private val repository = MovieRepository(movieDao)
    private val movieList = MutableLiveData<List<Movie>>()

    fun fetchMovie(name: String) {
        viewModelScope.launch {
            movieList.value = repository.movieSearch(name)
        }
    }

    fun getMovieListVM(): LiveData<List<Movie>> = movieList

    fun fetchMovieByTitle(name: String) {


    }

    //fun getMovieResult(): LiveData<Movie> = movieResult


    fun insert(movie: Movie) = viewModelScope.launch {

    }


}
