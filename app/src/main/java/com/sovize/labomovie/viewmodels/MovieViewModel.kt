package com.sovize.labomovie.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sovize.labomovie.database.RoomDB
import com.sovize.labomovie.database.entities.Movie
import com.sovize.labomovie.repositories.MovieRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.Date

class MovieViewModel(app: Application) : AndroidViewModel(app) {

    private val movieDao = RoomDB.getDatabase(app).movieDao()
    private val repository = MovieRepository(movieDao)
    val movieList = MutableLiveData<List<Movie>>()
    var cJob: Job? = null

    fun fetchMovie(name: String) {
        cJob?.cancel()
        cJob = viewModelScope.launch {
            val result = repository.movieSearch(name)
            movieList.value = result
            writeToDb(result)
        }
    }

    private suspend fun writeToDb(data: List<Movie>) {
        data.forEach {
            it.date = Date().toString()
            repository.insert(it)
        }
    }

    fun loadCache() {
        viewModelScope.launch {
            movieList.value = repository.getAllFromRoomDB()
        }
    }


}
