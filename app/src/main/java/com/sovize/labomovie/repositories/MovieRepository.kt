package com.sovize.labomovie.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.sovize.labomovie.database.daos.MovieDao
import com.sovize.labomovie.database.entities.Movie
import kotlinx.coroutines.Deferred
import retrofit2.Response

class MovieRepository(private val movieDao: MovieDao
                      //, private val api: OmbdApi
){

    //fun retrieveMoviesByNameAsync(name:String): Deferred<Response<OmbdMovieResponse>> = api.getMoviesByName(name)

    //fun retrieveMoviesByTitleAsync(name:String): Deferred<Response<Movie>> = api.getMovieByTitle(name)

    @WorkerThread
    suspend fun insert(movie: Movie) = movieDao.insertMovie(movie)

    fun getAllfromRoomDB(): LiveData<List<Movie>> = movieDao.loadAllMovies()

    fun getMovieByName(name: String) = movieDao.searchMovieByName(name)
}