package com.sovize.labomovie.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sovize.labomovie.database.RoomDB
import com.sovize.labomovie.database.entities.Movie
import com.sovize.labomovie.repositories.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(val app: Application) : AndroidViewModel(app) {

    private val repository: MovieRepository

    init {
        val movieDao = RoomDB.getDatabase(app).movieDao()
        //repository = MovieRepository(movieDao, ApiFactory.ombdApi)
    }

    private val scope = CoroutineScope(Dispatchers.IO)

    private val movieslist = MutableLiveData<MutableList<Movie>>()

    private val movieResult = MutableLiveData<Movie>()

    fun fetchMovie(name: String){
        scope.launch {
            val response=repository.retrieveMoviesByNameAsync(name).await()
            if(response.isSuccessful){
                when(response.code()){
                    200->movieslist.postValue(response.body()?.Search?.toMutableList()?:arrayListOf(
                        Movie(
                            Title = "Dummy 1"
                        ), MoviePreview(Title = "Dummy 2")))
                }
            }else{
                Toast.makeText(app, "Hi!!!! Algo Fallo :´v", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun getMovieListVM(): LiveData<MutableList<Movie>> = movieslist

    fun fetchMovieByTitle(name: String){
        scope.launch {
            val response=repository.retrieveMoviesByTitleAsync(name).await()
            if(response.isSuccessful) with(response){
                when(this.code()){
                    200->movieResult.postValue(this.body())
                }
            }else{
                Toast.makeText(app, "Hi!!!! Algo Fallo :´v", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun getMovieResult(): LiveData<Movie> = movieResult


    fun insert(movie: Movie) = scope.launch {
        repository.insert(movie)
    }

    fun getAll(): LiveData<List<Movie>> = repository.getAllfromRoomDB()

    fun getMovieByName(name: String): LiveData<List<Movie>> = repository.getMovieByName(name)

    //fun cancelAllRequests() = Dispatchers.IO.cancel()

}
