package com.sovize.labomovie.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sovize.labomovie.database.entities.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Query("select * from Movie order by date desc")
    suspend fun loadAllMovies(): List<Movie>

    @Query("select * from Movie where Title like :name")
    fun searchMovieByName(name: String): LiveData<List<Movie>>
}