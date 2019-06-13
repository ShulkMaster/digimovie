package com.sovize.labomovie.repositories

import android.util.Log
import androidx.annotation.WorkerThread
import com.sovize.labomovie.client.RetrofitCaller
import com.sovize.labomovie.client.drivers.MovieData
import com.sovize.labomovie.database.daos.MovieDao
import com.sovize.labomovie.database.entities.Movie
import com.sovize.labomovie.utils.AppLogger

class MovieRepository(private val movieDao: MovieDao) {


    suspend fun movieSearch(title: String) : List<Movie> {
        val caller = RetrofitCaller.getInstance(MovieData::class.java)
        val result = caller.searchTitleAsync(title).await()
        return if (result.isSuccessful){
            when (result.code()) {
                200 -> {
                    Log.d(AppLogger.retro, "Funciono ${result.body()}")
                    result.body()?.Search?.forEach {
                        Log.d(AppLogger.retro, "Funciono $it")
                    }
                    result.body()?.Search?:listOf(Movie())
                }
                else -> {
                    Log.d(AppLogger.retro, "Se conecto pero  ${result.errorBody()}")
                    result.body()?.Search?:listOf(Movie())
                }
            }
        } else{
            Log.d(AppLogger.retro, "Error de red")
            listOf(Movie())
        }
    }

    @WorkerThread
    suspend fun insert(movie: Movie) = movieDao.insertMovie(movie)

    @WorkerThread
    suspend fun getAllFromRoomDB(): List<Movie> = movieDao.loadAllMovies()

}